# spring_security
# spring security

环境：SpringBoot Jpa Redis
功能：

    登录
    注册
    消息列表
	权限管理


DO:

    Spring Security安全验证
	权限管理
	文章管理
	
###新增： 文章不再受限于char255的限制，现在存储在txt中，再也没有任何限制了！

TODO

这里使用的是硬编码的权限设置方式，要实现创建权限，实际上应该从数据库中设置和读取。
可以实现基于url和方法的动态权限管理。迫于时间，暂时搁置。

spring security 授权回顾

spring security 通过FilterChainProxy作为注册到web的filter，FilterChainProxy里面一次包含了内置的多个过滤器.
要实现动态鉴权，可以从两方面着手：

    自定义SecurityMetadataSource，实现从数据库加载ConfigAttribute
    自定义accessDecisionManager，官方的UnanimousBased其实足够使用，并且他是基于AccessDecisionVoter来实现权限认证的，因此我们只需要自定义一个AccessDecisionVoter就可以了

https://www.cnblogs.com/xiaoqi/p/spring-security-rabc.html

