
# 解决类命名困难的问题，同时达到望文生义的效果：比如这里对SpApp对象在不同层不同作用的类使用不同后缀来命名，如SpAppModel、SpAppMapper、ISpAppService、SpAppCtl等

im.yixin
    .sp
        control：contrl层的类
            exception: 定义control相关的异步
            interception：定义拦截器
        domain：此接口定义了和其它服务共用的类，实际应用时应该定义在基础jar包中
            sp: 以下所属的服务
                dto：和其它服务交互的使用的类，主要被应用在Control层的RequestMapper方法上
                mq：定义MQ消息
                qry: 特殊的dto，只用于封装查询参数
        logic：logic层接口类
            impl:logic层的实现类
        mapper: DAO层类
        model：和数据库一一对应的类
        mq: MQ相关的类
        remote：调用内部其它服务的类
            authority,notify：要调用的服务
        third：向第三发送请求，包名可自定义
            req: 封装向第三方请求的参数
            rsp: 第三方返回的参数
        service: 定义service内容
            impl: service的实现类
        xxjjob：分布式任务相关的类