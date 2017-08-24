#小语过期提醒

##表结构

```sql lite
create table objs(_id integer primary key autoincrement,title text,text text,tag text,expire_date text
```

> _id：id（自动增长）
>
> title：标题
>
> text：备注
>
> tag： 标签

为什么做小语过期提醒？

每周工作报告：

第一周：扫码查询药物

制作了一款通过扫描条形码达到查询药品的app

第二周：药品过期提醒

因为条形码数据库不够，所以暂时重点突出功能，即过期管理。

第三周：小语过期提醒

通过数据，发现有添加食物的，也就是说，用户的真实需求，不仅仅是药品过期。

不仅仅是药，也可以是食品，是优惠券，也可以是会员卡，甚至是项目截止日期等。

