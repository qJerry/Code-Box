package com.github.qjerry;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.auto.service.AutoService;
import com.google.common.collect.Lists;
import com.sun.source.tree.Tree;
import com.sun.tools.javac.api.JavacTrees;
import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.tree.TreeTranslator;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.processing.*;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Set;

/**
 * <p>Title:API-CodeBox</p>
 * <p>Desc: @CodeBoxData注解处理器，同步将业务错误码同步到code.json中，若项目本身就是配置json错误码，则不用该处理器</p>
 *
 * @author Jerry
 * @version 1.0
 * @since 2020/4/18
 */
@Slf4j
@SupportedAnnotationTypes({"com.github.qjerry.CodeBoxData"})
@AutoService(Processor.class)
public class CodeProcessor extends AbstractProcessor {

    private JavacTrees trees;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        log.debug("初始化注解处理器");
        super.init(processingEnv);
        this.trees = JavacTrees.instance(processingEnv);
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        Set<? extends Element> set = roundEnv.getElementsAnnotatedWith(CodeBoxData.class);
        if(set.size() > 0) {
            log.debug("开始处理业务枚举类");
        }
        ArrayList<JSONObject> list = Lists.newArrayList();
        set.forEach(element -> {
            JCTree jcTree = trees.getTree(element);
            jcTree.accept(new TreeTranslator() {
                @Override
                public void visitClassDef(JCTree.JCClassDecl jcClassDecl) {
                    for (JCTree tree : jcClassDecl.defs) {
                        if (tree.getKind().equals(Tree.Kind.VARIABLE)) {
                            JCTree.JCVariableDecl jcVariableDecl = (JCTree.JCVariableDecl) tree;
                            if(Objects.nonNull(jcVariableDecl.init)) {
                                JCTree.JCNewClass jcNewClass = (JCTree.JCNewClass) jcVariableDecl.init;
                                // 获取枚举类的初始化值
                                log.debug(String.valueOf(jcNewClass.getArguments()));
                                JSONObject obj = new JSONObject();
                                for(int i = 0; i < jcNewClass.getArguments().size(); i++) {
                                    JCTree.JCExpression arg = jcNewClass.getArguments().get(i);
                                    JCTree.JCLiteral jcLiteral = (JCTree.JCLiteral) arg;
                                    obj.put(i == 0 ? "code" : i == 1 ? "message" : "detail", jcLiteral.getValue());
                                }
                                list.add(obj);
                            }
                        }
                    }
                    super.visitClassDef(jcClassDecl);
                }

            });
        });
        // 生成json文件
        if(null != list && list.size() > 0) {
            String path = this.getClass().getResource("/").getPath();
            String fileName = path + "/code.json";
            boolean create = FileUtils.create(fileName);
            if(! create)
                log.error("生成json文件失败！");
            else {
                boolean write = FileUtils.write(fileName, JSON.toJSONString(list));
                if(write)
                    log.debug("业务错误码处理完成！");
            }

        }


        return true;
    }

}
