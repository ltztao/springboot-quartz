# spring boot 集成 quartz 定时任务

项目特点：
* 数据持久化到mysql  
* 动态新增quartz任务  

注意问题点：
* 官网提供的 quartz mysql sql 当数据库格式为utf-8时需要自行修改主键索引长度或者将数据库格式修改为gbk
* quartz sql 在当前项目 resources 下 tables_mysql.sql
* 支持组件启动多实例
    ```$xslt
    jobStore:
      acquireTriggersWithinLock: true # 多实例加锁
    ```
