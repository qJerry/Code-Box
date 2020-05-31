# Code-Box

Code-Box, born for error codes.<br />

<a name="TyxzH"></a>
## 背景
在业务开发中，经常会出现以下现象：

- xxx失败了，帮忙看看什么问题
- 怎么会出现这个错误
- 这个提示不友好，得改
- 错误信息，该前端处理，后端处理，还是中台处理？
- 多个服务之间错误码一致，错误内容不一致
- 。。。


<br />基于以上，我就下定决心，想做一款错误码系统，解决以上问题。这个系统，我称它为Code-Box 码盒。<br />

<a name="vrf68"></a>
## 什么是错误码？
错误码，对于很多人而言就是一串数字，它关联了系统上的各种错误信息。<br />
<br />错误码带给我们的作用是什么？

- 告知我们哪个系统哪个模块出了问题；
- 告知我们具体是什么问题；
- 通过决策和话术转换回显合理的错误内容给用户。



<a name="ybvTG"></a>
## 错误码服务的人群

- 对于服务间的调用，即针对技术人员


<br />![image.png](https://cdn.nlark.com/yuque/0/2020/png/243176/1581909865245-0fd083ff-47c3-4b75-bbac-c1ad9e8111ea.png#align=left&display=inline&height=159&margin=%5Bobject%20Object%5D&name=image.png&originHeight=159&originWidth=682&size=13082&status=done&style=none&width=682#align=left&display=inline&height=159&margin=%5Bobject%20Object%5D&originHeight=159&originWidth=682&status=done&style=none&width=682)<br />

- 对于客户端的调用，即针对非技术人员


<br />![image.png](https://cdn.nlark.com/yuque/0/2020/png/243176/1581909929407-1017b9b0-1422-4c6d-ad7e-40e8ed42e7c9.png#align=left&display=inline&height=161&margin=%5Bobject%20Object%5D&name=image.png&originHeight=161&originWidth=681&size=12721&status=done&style=none&width=681#align=left&display=inline&height=161&margin=%5Bobject%20Object%5D&originHeight=161&originWidth=681&status=done&style=none&width=681)<br />

<a name="cW5lJ"></a>
## 错误码类型
![image.png](https://cdn.nlark.com/yuque/0/2020/png/243176/1588486291183-acc1f079-b50f-47fe-994a-7befc6480140.png#align=left&display=inline&height=239&margin=%5Bobject%20Object%5D&name=image.png&originHeight=239&originWidth=482&size=9821&status=done&style=none&width=482)<br />

<a name="eeyl1"></a>
## 错误码设计
错误码设计原则是，尽可能做到简洁。故我将错误码分成两部分：系统码（前4位）+错误码（后4位）<br />系统码和错误码区间区间 需通过后台申请。<br />![image.png](https://cdn.nlark.com/yuque/0/2020/png/243176/1588486466975-c2c05977-7f33-49e3-8446-d5494e64ac55.png#align=left&display=inline&height=361&margin=%5Bobject%20Object%5D&name=image.png&originHeight=361&originWidth=562&size=20319&status=done&style=none&width=562)<br />由于要区分错误码类型，我定义了指定的区间进行分配，剩下未分配的区间，待后续动态扩展<br />
<br />例如，我所在部门假设会有50个项目来开发，那么我可以申请区间为00010000-00500000的错误码。当然后续由于业务扩展，又增加了新的项目，亦可以重新申请新的错误码区间！<br />

<a name="2uuq3"></a>
## 领域模型
我考虑了两种场景，对内错误码API和开放错误码API。<br />基础的模型有：运营后台、错误码系统、错误码服务
<a name="kv6FM"></a>
### 对内错误码
贯彻“错误码无侵入”的理念，对内设计的领域模型，主要有业务系统—>错误码SDK<—>错误码内部API
<a name="XkX0q"></a>
### 开放错误码
我们仅需要将错误码API接口开放即可，并 提供一套错误码API文档<br />![image.png](https://cdn.nlark.com/yuque/0/2020/png/243176/1588498287238-2e0363eb-e718-4f33-bc56-6602e483e4ad.png#align=left&display=inline&height=560&margin=%5Bobject%20Object%5D&name=image.png&originHeight=560&originWidth=1001&size=37455&status=done&style=none&width=1001)<br />

<a name="P3eEF"></a>
## 数据库设计
![image.png](https://cdn.nlark.com/yuque/0/2020/png/243176/1588495621963-ec565c00-55cf-4f61-a80a-8a9e64007ff4.png#align=left&display=inline&height=718&margin=%5Bobject%20Object%5D&name=image.png&originHeight=718&originWidth=1161&size=91009&status=done&style=none&width=1161)<br />

<a name="vFpv2"></a>
## 项目架构
码盒项目基于Springboot + Spring cloud + Mysql搭建而成，项目架构比较简单。<br />![image.png](https://cdn.nlark.com/yuque/0/2020/png/243176/1588487357922-814f9fa7-62dd-44b9-85c8-92263f9f953b.png#align=left&display=inline&height=478&margin=%5Bobject%20Object%5D&name=image.png&originHeight=643&originWidth=1003&size=48545&status=done&style=none&width=746)<br />

<a name="vqdYr"></a>
### 运营后台

- 创建业务部门、业务系统
- 申请端口、系统码、错误码区间
- 修改错误码信息、及其错误描述
- 售后排查功能
<a name="s6Kq9"></a>
### 错误码服务

- 内部错误码API接口
- 外部错误码API接口
- 错误码文档可视化
<a name="PFghE"></a>
### 错误码提取
支持在编译期间，从业务中自动提取出错误码内容
<a name="cHdkr"></a>
### 错误码SDK
接入业务系统，通过配置中心配置，开启错误码同步及检测功能，并支持开启周期性更新错误码，错误日志同步...
<a name="xoel4"></a>
### 错误码话术转换
待定。。。<br />
<br />
<br />

<a name="fsVRc"></a>
## 业务流程
![image.png](https://cdn.nlark.com/yuque/0/2020/png/243176/1588488831390-1e0b2e66-35d9-45cf-aa94-ed7a58895462.png#align=left&display=inline&height=799&margin=%5Bobject%20Object%5D&name=image.png&originHeight=799&originWidth=1002&size=61758&status=done&style=none&width=1002)<br />从上图看出，我这里区分了4个角色：项目负责人、产品、技术、用户<br />

<a name="8MBao"></a>
### 负责人
这是接入码盒的第一步

- 创建业务部门：根据部门信息，对应创建一条部门记录
- 创建业务系统：根据系统信息，对应创建一条系统记录，并自动生成一个系统码，4位组成
- 申请配置：这里不仅可以申请错误码区间，还可以申请域名，端口等，这样可以协同管理跨部门的信息


<br />当申请配置成功后，即可进行下一步
<a name="1gd5n"></a>
### 技术线
引进码盒SDK即可，但是配置方式有以下几种
<a name="wExmJ"></a>
#### 配置错误码文件
这是最简单的一种接入方式。技术人员在具体的业务项目中引入码箱SDK，并在资源文件下定义code.json，内容参考如下
```json
[
  {
    "code": 101,
    "message": "参数有误",
    "detail": "请检查入参：必填参数是否为空，长度超出规定限制长度 或 是否不符合格式"
  },
  {
    "code": 102,
    "message": "系统繁忙",
    "detail": "可能发生了网络或者系统异常，导致无法判定准确的转账结果。此时，商户不能直接当做转账成功或者失败处理，可以考虑采用相同的out_biz_no重发请求，或者通过调用“(alipay.fund.trans.order.query)”来查询该笔转账订单的最终状态"
  },
  {
    "code": 103,
    "message": "根据监管部门的要求，请补全您的身份信息解除限制",
    "detail": "根据监管部门的要求，请补全您的身份信息解除限制"
  }
]
```


<a name="aW6rv"></a>
#### 配置注解
常见的项目，会将错误码定义在枚举类中，增加了可读性，以及便于迭代。<br />这里针对此业务错误码枚举类，增加了自定义注解 `@CodeBoxData` 的支持，如下。当然有些项目错误码多，需要按业务进行区分，即有多个枚举类，也一样在每个枚举类上添加 `@CodeBoxData` 注解。<br />
<br />配置注解的目的，也是为了生成错误码信息到资源文件下的code.json文件中。<br />至于怎么生成，这里主要借鉴了lombok、mapstruct等的思路，它们基于注解处理器，给我们提供了很多丰富的功能。<br />在编译期间，码盒的注解处理器将会将枚举类的内容动态添加到code.json。接着等项目启动，SDK将会将生成的错误码同步到码盒系统。<br />

<a name="rmQAn"></a>
### 产品线
码盒提供了针对产品经理的一个解决方案，在公司产品经理和开发人员经常有这样几个苦恼：

1. 开发人员定义的错误信息太技术，导致涌出部分售后问题，引发产品不满：“这不是我设计的！”；
1. 修改错误信息，必须改代码，重新发布，引发开发人员不满：“又来！”；
1. 错误信息应该从哪里处理，前端？后端？中台？，引发开发人员疑惑：“是你，是你，还是你？”；


<br />以上问题，在码盒上统统解决，当发现错误信息会带来售后问题而需要优化时，仅需要在码盒后台修改即可！<br />

<a name="3lZjy"></a>
## 业务接入
<a name="eUnVy"></a>
### 前提
业务项目基于Springboot + Spring cloud 框架<br />

<a name="5tmFC"></a>
### 下载码盒项目
[https://github.com/qJerry/Code-Box](https://github.com/qJerry/Code-Box)<br />

<a name="Y927q"></a>
### 执行数据库脚本（MYSQL）
![image.png](https://cdn.nlark.com/yuque/0/2020/png/243176/1588496780981-63ca66bb-315c-4e90-98b7-4a0f1a04fd2e.png#align=left&display=inline&height=355&margin=%5Bobject%20Object%5D&name=image.png&originHeight=355&originWidth=428&size=18805&status=done&style=none&width=428)<br />

<a name="WwDRB"></a>
### 更改码盒内部API配置文件
数据库配置、端口配置、注册中心配置
```yaml
spring:
  datasource:
    hikari:
      username: root
      password: 123456
    url: jdbc:mysql://localhost:3306/code_box?characterEncoding=utf-8&serverTimezone=GMT%2B8

server:
  port: 8501

eureka:
  client:
    service-url:
      defaultZone: http://localhost:1002/eureka/
```


<a name="A0WFd"></a>
### 引入SDK
```xml
<dependencies>
  <dependency>
    <groupId>com.github.qJerry</groupId>
    <artifactId>CodeBox-Sdk</artifactId>
    <version>1.0-SNAPSHOT</version>
  </dependency>
</dependencies>
```


<a name="btjRy"></a>
### 添加自定义注解@CodeBoxData
在错误码枚举类上添加自定义注解，枚举类的参数参考如下。<br /><br />注意：如上所说，错误码分为系统码（前4位）+错误码（后4位），在业务项目上，错误码无需考虑系统码，故在定义上只要定义后4位即可。
```java
@Getter
@CodeBoxData
public enum BusinessCodeEnum {

    // 设备：1000-1999
    EQUIPMENT_NOT_EXIST(1000, "设备不存在", ""),
    
    // 用户：2000-2499
    USER_LOGIN_AGAIN(2000, "请重新登录", "当前缓存信息已过期，需要重新登录");

    Integer code;
    String message;
    String detail;

    BusinessCodeEnum(Integer code, String message, String detail) {
        this.code = code;
        this.message = message;
        this.detail = detail;
    }
}
```


<a name="wfh0H"></a>
### 业务项目配置文件添加码盒配置
```yaml
code:
  business:
    id: 1	// 业务部门id
    system:
      id: 1	// 业务系统id
  refresh:
    open: false	// 是否开启周期性任务
    cron: 0 0/5 * * * ?	// 调度周期
  sync-log: true // 是否同步错误日志
```


<a name="NLHZd"></a>
### 实际使用
在每个需要用到错误码的地方，直接调用SDK的 `ResultVO.err()` 输入具体的错误码即可，如下
```java
public ResultVO getCode(String name) {
  if(Strings.isNullOrEmpty(name) || ! name.equals("Jerry")) {
  	return ResultVO.err(10101L);
	}
	return ResultVO.suc();
}
```
当然，SDK也内置了全局异常处理器`CodeBoxGlobalException`，直接传入错误码即可。
```java
throw new CodeBoxGlobalException(code);
```


<a name="y6oBK"></a>
## 框架特色
<a name="pTn79"></a>
### 99%无侵入
为了尽可能做到不侵入业务代码，我制作了一个SDK，并添加了自定义注解或者直接配置文件的形式进行接入<br />

<a name="GS9he"></a>
### 自定义注解处理器
支持对于在业务错误码枚举类上添加了自定义注解的类，将在编译期间，将其拉取到资源文件下的code.json中，业务技术方无需手写code.json文件，只需添加注解，即可快速完成接入的一大步。<br />

<a name="u43Ed"></a>
### 动态字节码技术（对外透明化）
内部封装了一个错误码工具类，使用了ASM动态字节码功能，在项目启动期间和周期性刷新错误码期间，将会动态添加或更新错误码到SDK的错误码枚举类中，对外透明化，业务仅需提供一个错误码作为入参，即可获取详细的错误码内容。<br />**出发点：**<br />可能有些人包括我一开始会直接使用redis缓存来存放错误码信息。但从实际使用上看，错误码更多的是静态数据，一般不会去频繁改动，这时候放置于缓存就有点浪费资源了。故我在做自定义注解处理器的时候，就想到了使用动态字节码技术，在运行期间放置或修改错误码信息。<br />

<a name="BnrZa"></a>
### 周期性刷新错误码
当在后台修改对应的错误码信息后，若开启了周期性刷新，则会定时同步最新数据到本地~~<br />

<a name="P90Ko"></a>
### 个性化配置
可以配置是否要同步错误码日志到平台、配置接入码盒的方式、配置是否要定时更新后台同步的错误码信息等等<br />

<a name="Eh02J"></a>
### 日志排查功能
当配置了同步错误码日志到平台后，即可在后台查看具体的错误信息，并给出具体的解决方案。<br />

<a name="If95A"></a>
### 错误日志导出

<br />
<br />

<a name="slp4k"></a>
## 项目状况
<a name="SZVna"></a>
### 已完成事项
已完成码盒服务、码盒SDK的开发<br />

<a name="fMiLc"></a>
### 未完成事项
运营后台相关功能。<br />

<a name="rOATz"></a>
### github地址
[https://github.com/qJerry/Code-Box](https://github.com/qJerry/Code-Box)
