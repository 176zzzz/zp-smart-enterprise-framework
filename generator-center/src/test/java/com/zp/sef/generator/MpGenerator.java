package com.zp.sef.generator;


import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;
import com.baomidou.mybatisplus.generator.fill.Column;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;

/**
 * CreateCrud 参考: https://blog.csdn.net/qq_20185737/article/details/121456598
 *
 * @author ZP
 */
@SuppressWarnings("all")
public class MpGenerator {

    private static final String URL = "jdbc:mysql://81.70.252.108:3306/user_center?useUnicode=true&characterEncoding=utf-8&allowMultiQueries=true&useSSL=false&serverTimezone=Asia/Shanghai";

    private static final String USERNAME = "root";

    private static final String PASSWORD = "admin050399";

    /**
     * 与当前代码生成器项目目录的相对地址,(比如跟它平级的xxx项目模块,这里就写"../xxx" )
     */
    private static final String RELATIVE_PATH_PREFIX = "../user-center";

    //父包名
    private static final String PARTENT_NAME = "com.zp.sef.user1";

    //作者名
    private static final String USER_NAME = "ZP";

    //包名配置
    private static final String ENTITY_PACKAGE = "model.entity";
    private static final String SERVICE_PACKAGE = "service";
    private static final String SERVICE_IMPL_PACKAGE = "service.impl";
    private static final String MAPPER_PACKAGE = "mapper";
    private static final String CONTROLLER_PACKAGE = "controller";


    /**
     * mybatis-plus-generator-3.5以上版本
     */
    @Test
    public void generate() {

        System.out.println(
                "当前数据源信息: \nurl:" + URL + "\nusername" + USERNAME + ",\npassword" + PASSWORD + "\n");

        FastAutoGenerator
                // 1.数据源
                .create(URL, USERNAME, PASSWORD)

                // 2、全局配置
                .globalConfig(builder -> {
                    builder.author(USER_NAME) // 设置作者名
                            .outputDir(System.getProperty("user.dir") + "/" + RELATIVE_PATH_PREFIX
                                    + "/src/main/java")   //设置输出路径
                            .commentDate("yyyy-MM-dd hh:mm:ss")   //注释日期
                            .dateType(DateType.TIME_PACK)   //定义生成的实体类中日期的类型 TIME_PACK=LocalDateTime;ONLY_DATE=Date;
                            .enableSwagger()   //开启 swagger 模式
                            .disableOpenDir();   //禁止打开输出目录，默认打开
                })

                //3、包配置
                .packageConfig(builder -> {
                    builder.parent(PARTENT_NAME) // 设置父包名
                            // .moduleName("xxx")   //设置模块包名
                            .entity(ENTITY_PACKAGE)   // pojo 实体类包名
                            .service(SERVICE_PACKAGE) // Service 包名
                            .serviceImpl(SERVICE_IMPL_PACKAGE) // ***ServiceImpl 包名
                            .mapper(MAPPER_PACKAGE)   // Mapper 包名
                            .controller(CONTROLLER_PACKAGE) // Controller 包名
                    ;

                })

                //4、策略配置
                .strategyConfig((scanner, builder) -> {
                    builder.addInclude(getTables(scanner.apply(
                                    "=====================策略配置=======================\n请输入表名，多个英文逗号分隔？当前库所有表请输入: all"))) // 设置需要生成的数据表名
                            // .addTablePrefix("test_", "record_") // 设置过滤表前缀

                            //4.1、Mapper策略配置
                            .mapperBuilder()
                            .superClass(BaseMapper.class)   //设置父类
                            .formatMapperFileName("%sMapper")   //格式化 mapper 文件名称
                            .enableMapperAnnotation()       //开启 @Mapper 注解
                            // .formatXmlFileName("%sXml") //格式化 Xml 文件名称

                            //4.2、service 策略配置
                            .serviceBuilder()
                            .formatServiceFileName("%sService") //格式化 service 接口文件名称，%s进行匹配表名，如 UserService
                            .formatServiceImplFileName(
                                    "%sServiceImpl") //格式化 service 实现类文件名称，%s进行匹配表名，如 UserServiceImpl

                            //4.3、实体类策略配置
                            .entityBuilder()
                            .javaTemplate("/templates/entitySwagger3.java.vm")
                            .enableLombok() //开启 Lombok
                            //.disableSerialVersionUID()  //不实现 Serializable 接口，不生产 SerialVersionUID
                            .logicDeleteColumnName("deleted")   //逻辑删除字段名
                            .idType(IdType.ASSIGN_ID)
                            .naming(NamingStrategy.underline_to_camel)  //数据库表映射到实体的命名策略：下划线转驼峰命
                            .columnNaming(NamingStrategy.underline_to_camel)    //数据库表字段映射到实体的命名策略：下划线转驼峰命
                            .addTableFills(
                                    new Column("create_time", FieldFill.INSERT)
                                    // new Column("modify_time", FieldFill.INSERT_UPDATE)
                            )   //添加表字段填充，"create_time"字段自动填充为插入时间，"modify_time"字段自动填充为插入修改时间
                            .enableTableFieldAnnotation()       // 开启生成实体时生成字段注解

                            //4.4、Controller策略配置
                            .controllerBuilder()
                            .template("/templates/controller.java.vm")
                            .formatFileName("%sController") //格式化 Controller 类文件名称，%s进行匹配表名，如 UserController
//                           .formatFileName("") //格式化 Controller 类文件名称，%s进行匹配表名，如 UserController
                            .enableRestStyle();  //开启生成 @RestController 控制器


                })

                //5、模板引擎
                //默认使用VVelocity模板引擎，可以自定义模板引擎，比如：Freemarker 或 Beetl
                .templateEngine(new VelocityTemplateEngine())

                //6、执行
                .execute();
    }

    // 处理 all 情况
    protected static List<String> getTables(String tables) {
        return "all".equals(tables.trim()) ? Collections.emptyList()
                : Arrays.asList(tables.split(","));
    }
}
