# 产品后台数据管理系统
## 技术
采用Spring MVC+Spring+Mybatis+Maven搭建
## 主要功能
该系统主要实现了产品,订单的管理,以及采用了Spring Security对用户的权限管理,同时用AOP完成日志的采集
## 负责模块
* 分页查询  
采用Mybatis的分页插件PageHelper优化分页查询
* 权限管理  
用Spring Security进行权限拦截,用户登录与退出
* 日志处理  
用Spring的Aop完成全局的日志处理
