分布式事务--分布式事务

1、添加jar的支持

        <dependency>
            <groupId>org.apache.shardingsphere</groupId>
            <artifactId>sharding-jdbc-spring-boot-starter</artifactId>
            <version>4.0.0-RC2</version>
        </dependency>

2、配合spring的事务注解
        @Transactional
3、在service显示设置事务类型：
         @ShardingTransactionType(TransactionType.XXXX)
4、在catch显示回滚事务：
        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();

例：
    @Transactional
    @ShardingTransactionType(TransactionType.XA)
    public String test(...) {
        try {
            ...
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return "XXX";
    }





TransactionType.XA; 两阶段提交
TransactionType.LOCAL; 本地事务
TransactionType.BASE; 软状态  最终一致性

        本地事务	        两（三）阶段事务	    柔性事务
业务改造	无	            无	                实现相关接口
一致性	不支持	        支持	                最终一致
隔离性	不支持	        支持	                业务方保证
并发性能	无影响	        严重衰退	            略微衰退
适合场景	业务方处理不一致	短事务 & 低并发	    长事务 & 高并发




官方文档：https://shardingsphere.apache.org/document/current/cn/manual/sharding-jdbc/
理论参考：https://blog.csdn.net/u014590757/article/details/80100085
