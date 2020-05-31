package com.github.qjerry.utils;

import com.github.qjerry.dto.StatusCodeDTO;
import com.github.qjerry.enums.StatusCodeEnum;
import com.github.qjerry.vo.BusinessCodeVO;
import jdk.internal.org.objectweb.asm.*;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

import static jdk.internal.org.objectweb.asm.Opcodes.*;

/**
 * <p>Title:API-CodeBox</p>
 * <p>Desc: </p>
 *
 * @author Jerry
 * @version 1.0
 * @since 2020/4/28
 */
public class CodeUtils {

    private static Class localClassByBytes;

    /**
     * 获取错误码内容
     * @param code
     * @return
     */
    public static StatusCodeDTO getCode(Long code) {
        StatusCodeDTO codeEnum;
        try {
            Method method = localClassByBytes.getMethod("getStatusCode", Long.class);
            codeEnum = (StatusCodeDTO) method.invoke(null, code);
        } catch (Exception e) {
//            e.printStackTrace();
            codeEnum = StatusCodeDTO.create(-1L, "系统错误", "系统错误");
        }
        return codeEnum;
    }

    /**
     * 动态添加错误码
     * @param codes
     * @throws Exception
     */
    public static void dynamicAddCode(List<BusinessCodeVO> codes) throws Exception {
        // 补上原有枚举类上的错误码
        Arrays.stream(StatusCodeEnum.values()).forEach(code -> {
            codes.add(new BusinessCodeVO(code.getCode(), code.getMessage(), code.getDetail()));
        });
        ClassReader classReader = new ClassReader(StatusCodeEnum.class.getName());
        ClassWriter classWriter = new ClassWriter(classReader, 0);
        ClassVisitor classVisitor = new MyClassVisitor(classWriter, codes);
        classReader.accept(classVisitor, 0);
        MyClassLoader myClassLoader = new MyClassLoader();
        Class classByBytes = myClassLoader.getClassByBytes(classWriter.toByteArray());
        localClassByBytes = classByBytes;
    }

    public static class MyClassLoader extends ClassLoader {
        public Class getClassByBytes(byte[] bytes) {
            return defineClass(null, bytes, 0, bytes.length);
        }
    }

    public static class MyClassVisitor extends ClassVisitor {

        List<BusinessCodeVO> codes;

        public MyClassVisitor(ClassVisitor classVisitor, List<BusinessCodeVO> codes) {
            super(ASM5, classVisitor);
            this.codes = codes;
        }

        @Override
        public void visitSource(String s, String s1) {
            super.visitSource(s, s1);
            codes.stream().forEach(code -> {
                if(code.getCode() == 0) {
                    return;
                }
                FieldVisitor fv = cv.visitField(ACC_PUBLIC + ACC_FINAL + ACC_STATIC + ACC_ENUM, "CODE_" + code.getCode(), "Lcom/github/qjerry/enums/StatusCodeEnum;", null, null);
                fv.visitEnd();
            });
        }

        @Override
        public MethodVisitor visitMethod(int i, String s, String s1, String s2, String[] strings) {
            if(s.equals("<clinit>")){
                return new MyMethodVisitor(cv, codes);
            }
            MethodVisitor methodVisitor = super.visitMethod(i, s, s1, s2, strings);
            return methodVisitor;
        }
    }

    public static class MyMethodVisitor extends MethodVisitor {

        private ClassVisitor cv;
        private List<BusinessCodeVO> codes;

        public MyMethodVisitor(ClassVisitor cv, List<BusinessCodeVO> codes) {
            super(ASM5, null);
            this.cv = cv;
            this.codes = codes;
        }

        @Override
        public void visitCode() {
            MethodVisitor mv = cv.visitMethod(ACC_STATIC, "<clinit>", "()V", null, null);
            mv.visitCode();
            int index = 0;
            for (BusinessCodeVO code : codes) {
                Label l0 = new Label();
                mv.visitLabel(l0);
                mv.visitTypeInsn(NEW, "com/github/qjerry/enums/StatusCodeEnum");
                mv.visitInsn(DUP);
                mv.visitLdcInsn("CODE_" + code.getCode());
                pushInstruction(mv, index);
                mv.visitLdcInsn(new Long(code.getCode()));
                mv.visitMethodInsn(INVOKESTATIC, "java/lang/Long", "valueOf", "(J)Ljava/lang/Long;", false);
                mv.visitLdcInsn(code.getMessage());
                mv.visitLdcInsn(code.getDetail());
                mv.visitMethodInsn(INVOKESPECIAL, "com/github/qjerry/enums/StatusCodeEnum", "<init>", "(Ljava/lang/String;ILjava/lang/Long;Ljava/lang/String;Ljava/lang/String;)V", false);
                mv.visitFieldInsn(PUTSTATIC, "com/github/qjerry/enums/StatusCodeEnum", "CODE_" + code.getCode(), "Lcom/github/qjerry/enums/StatusCodeEnum;");
                index ++;
            }
            Label l4 = new Label();
            mv.visitLabel(l4);
            mv.visitIntInsn(BIPUSH, index);
            mv.visitTypeInsn(ANEWARRAY, "com/github/qjerry/enums/StatusCodeEnum");
            for (int i = 0; i < codes.size(); i++) {
                BusinessCodeVO code = codes.get(i);
                mv.visitInsn(DUP);
                pushInstruction(mv, i);
                mv.visitFieldInsn(GETSTATIC, "com/github/qjerry/enums/StatusCodeEnum", "CODE_" + code.getCode(), "Lcom/github/qjerry/enums/StatusCodeEnum;");
                mv.visitInsn(AASTORE);
            }
            mv.visitFieldInsn(PUTSTATIC, "com/github/qjerry/enums/StatusCodeEnum", "$VALUES", "[Lcom/github/qjerry/enums/StatusCodeEnum;");
            mv.visitInsn(RETURN);
            mv.visitMaxs(100, 0);
            mv.visitEnd();
        }

        @Override
        public void visitMaxs(int i, int i1) {
        }
    }

    public static void pushInstruction(MethodVisitor mv, int index) {
        if(index >= -1 && index <= 5) {
            mv.visitInsn(ICONST_0 + index);
        } else if(index >= -128 && index <= 127) {
            mv.visitIntInsn(BIPUSH, index);
        } else if(index >= -32768 && index <= 32767) {
            mv.visitIntInsn(SIPUSH, index);
        } else {
            mv.visitIntInsn(LDC, index);
        }
    }
}
