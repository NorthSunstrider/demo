# 说明：在demo-dubbo-consumer基础上的扩展项目，将注册中心切换为zookeeper  
重点：可配合dubbo-admin项目监控管理服务  
dubbo-admin项目原在git上的dubbo仓库中，但在2.6版本后因未知原因移除，现仍可在2.5等老版本中找到  
找到dubbo-admin项目，使用mvn package命令或其他方法打成war包，发布在tomcat中即可使用dubbo-admin监控管理服务  

切换为zookeeper需要在demo-dubbo-api项目上引入更多的jar包  
手动尝试了下，引入zookeeper.jar和cuator.jar等jar包后依旧报错  
所以直接使用了网上demo的所有jar包  