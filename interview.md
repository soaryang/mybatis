##{}和${}的区别是什么？
{}是预编译处理，${}是字符串替换。
Mybatis在处理#{}时，会将sql中的#{}替换为?号，调用PreparedStatement的set方法来赋值；
Mybatis在处理${}时，就是把${}替换成变量的值。
使用#{}可以有效的防止SQL注入，提高系统安全性。

##当实体类中的属性名和表中的字段名不一样 ，怎么办 ？
    通过在查询的sql语句中定义字段名的别名，让字段名的别名和实体类的属性名一致。
    <select id=”selectorder” parametertype=”int” resultetype=”me.gacl.domain.order”>
           select order_id id, order_no orderno ,order_price price form orders where order_id=#{id};
        </select>    
    通过<resultMap>来映射字段名和实体类属性名的一一对应的关系。
    <select id="getOrder" parameterType="int" resultMap="orderresultmap">
            select * from orders where order_id=#{id}
        </select>
     
   <resultMap type=”me.gacl.domain.order” id=”orderresultmap”>
        <!–用id属性来映射主键字段–>
        <id property=”id” column=”order_id”>
 
        <!–用result属性来映射非主键字段，property为实体类属性名，column为数据表中的属性–>
        <result property = “orderno” column =”order_no”/>
        <result property=”price” column=”order_price” />
    </reslutMap>
    
##模糊查询like语句该怎么写?
    1.在Java代码中添加sql通配符。
    string wildcardname = “%smi%”;
    list<name> names = mapper.selectlike(wildcardname);
 
    <select id=”selectlike”>
     select * from foo where bar like #{value}
    </select>
    
    在sql语句中拼接通配符，会引起sql注入
     string wildcardname = “smi”;
        list<name> names = mapper.selectlike(wildcardname);
     
        <select id=”selectlike”>
             select * from foo where bar like "%"${value}"%"
        </select>


https://blog.csdn.net/a745233700/article/details/80977133