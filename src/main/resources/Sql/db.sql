create table exchange_rate
(
    currency_id varchar(20) not null comment '货币编号（英文缩写，有参考标准）',
    currency_name varchar(20) null comment '货币名称',
    currency_unit varchar(40) null comment '货币单位',
    currency_rate float null comment '与美元的汇率',
    constraint exchange_rate_currency_id_uindex
        unique (currency_id)
)
    comment '汇率表';

alter table exchange_rate
    add primary key (currency_id);

create table salesman
(
    salesman_id varchar(20) not null comment '交易员编号'
        primary key,
    salesman_name varchar(20) null comment '交易员名字',
    company_id varchar(20) null comment '交易员所属公司代号'
)
    comment '交易员表';

create table shares_class
(
    class_id int auto_increment comment '类别编号，自增'
        primary key,
    class_name varchar(20) null comment '类别名称'
)
    comment '股票所属行业类型表';

create table shares
(
    RIC varchar(20) not null comment '股票RIC编号'
        primary key,
    shares_name varchar(20) null comment '股票名称',
    class_id int null comment '股票的行业分类',
    shares_price float null comment '股票的单价',
    currency_id varchar(20) null comment '可交易的货币编号',
    shares_num bigint null comment '股票发行总数',
    trade_limit int null comment '个人持有最大数限制',
    shares_flag int null comment '状态符，标志是否可交易',
    constraint shares_exchange_rate_currency_id_fk
        foreign key (currency_id) references exchange_rate (currency_id),
    constraint shares_shares_class_class_id_fk
        foreign key (class_id) references shares_class (class_id)
)
    comment '股票信息表';

create table shares_history
(
    record_id int auto_increment comment '记录编号，自增'
        primary key,
    RIC varchar(20) null comment '股票RIC编号',
    closing_price float null comment '股票当天收盘价(Notional)',
    transaction_in bigint null comment '股票当天总买入数量',
    transaction_out bigint null comment '股票当天总卖出数量',
    record_date date null comment '记录时间',
    constraint shares_history_shares_RIC_fk
        foreign key (RIC) references shares (RIC)
)
    comment '股票记录表（日结）';

create table user
(
    user_id varchar(20) not null comment '用户id，唯一标识'
        primary key,
    user_name varchar(20) null comment '用户名字',
    login_name varchar(20) null comment '登陆用用户名',
    login_password varchar(60) null comment '登陆密码
',
    logon_time timestamp null comment '用户注册时间',
    flag int null comment '用户权限标志'
)
    comment '用户表';

create table transaction_records
(
    record_id int auto_increment comment '记录编号，自增'
        primary key,
    RIC varchar(20) null comment '股票RIC编号',
    user_id varchar(20) null comment '交易者id',
    transaction_size int null comment '交易数量',
    transaction_time datetime null comment '交易时间',
    salesman_id varchar(20) null comment '交易员编号',
    currency_id varchar(20) null comment '货币编号',
    transaction_price float null comment '股票成交单价',
    transaction_flag varchar(4) null comment '交易类型标志，buy or sell',
    shares_hold bigint null comment '用户当前的该股持有数（包含当前交易）',
    issuer_sector varchar(20) null comment '发行部门',
    transaction_mode int null comment '交易模式，1：HT，0：PT',
    constraint transaction_records_exchange_rate_currency_id_fk
        foreign key (currency_id) references exchange_rate (currency_id),
    constraint transaction_records_salesman_salesman_id_fk
        foreign key (salesman_id) references salesman (salesman_id),
    constraint transaction_records_shares_RIC_fk
        foreign key (RIC) references shares (RIC),
    constraint transaction_records_user_user_id_fk
        foreign key (user_id) references user (user_id)
)
    comment '交易记录表';

create definer = groupb@`%` view transaction_view as
select cast(`records`.`transaction_time` as date)                               AS `date`,
       `users`.`user_name`                                                      AS `client_name`,
       `records`.`transaction_flag`                                             AS `client_side`,
       `citi`.`shares`.`shares_name`                                            AS `ticker`,
       `citi`.`shares`.`RIC`                                                    AS `ric`,
       `records`.`transaction_size`                                             AS `size`,
       `records`.`transaction_price`                                            AS `price`,
       (`records`.`transaction_price` / `citi`.`exchange_rate`.`currency_rate`) AS `notional_usd`,
       `citi`.`exchange_rate`.`currency_name`                                   AS `currency`,
       `records`.`issuer_sector`                                                AS `issuer_sector`,
       `citi`.`salesman`.`salesman_name`                                        AS `salesperson`,
       `records`.`transaction_mode`                                             AS `ht_pt`
from ((((`citi`.`transaction_records` `records` join `citi`.`user` `users`) join `citi`.`shares`) join `citi`.`exchange_rate`)
         join `citi`.`salesman`)
where ((`records`.`RIC` = `citi`.`shares`.`RIC`) and (`records`.`user_id` = `users`.`user_id`) and
       (`records`.`salesman_id` = `citi`.`salesman`.`salesman_id`) and
       (`records`.`currency_id` = `citi`.`exchange_rate`.`currency_id`));

-- comment on column transaction_view.client_name not supported: 用户名字

-- comment on column transaction_view.client_side not supported: 交易类型标志，buy or sell

-- comment on column transaction_view.ticker not supported: 股票名称

-- comment on column transaction_view.ric not supported: 股票RIC编号

-- comment on column transaction_view.size not supported: 交易数量

-- comment on column transaction_view.price not supported: 股票成交单价

-- comment on column transaction_view.currency not supported: 货币名称

-- comment on column transaction_view.issuer_sector not supported: 发行部门

-- comment on column transaction_view.salesperson not supported: 交易员名字

-- comment on column transaction_view.ht_pt not supported: 交易模式，1：HT，0：PT


