create database sales;
use sales;
create table product_info (
  product_id          varchar(32)   not null
  comment '商品id',
  product_name        varchar(64)   not null
  comment '商品名称',
  product_price       decimal(8, 2) not null
  comment '商品单价',
  product_stock       int           not null
  comment '商品库存',
  product_description varchar(64)   not null
  comment '商品描述',
  product_icon        varchar(512)  not null
  comment '商品图标',
  category_type       int           not null
  comment '商品类目编号',
  create_time timestamp not null default current_timestamp comment '创建时间',
  update_time timestamp not null default current_timestamp on update current_timestamp comment '修改时间',
  primary key (product_id)

)comment '商品表';

create table product_category(
  category_id int not null auto_increment comment '类目id',
  category_name varchar(64) not null comment '类目名称',
  category_type int not null comment '类目编号',
  create_time timestamp not null default current_timestamp comment '创建时间',
  update_time timestamp not null default current_timestamp on update current_timestamp comment '修改时间',
  primary key (category_id),
  unique key uqi_category_type (category_type)
)comment '类目表';

create table order_master(
  order_id varchar(32) not null comment '订单id',
  buyer_name varchar(32) not null comment '买家地址',
  buyer_phone varchar(32) not null comment '买家电话',
  buyer_address varchar(128) not null comment '买家地址',
  buyer_openid varchar(64) not null comment '买家微信openid',
  order_amount decimal(8,2) not null comment '订单总金额',
  order_status tinyint(3) not null default '0' comment '订单状态,默认0新下单',
  pay_status tinyint(3) not null default '0' comment '支付状态，默认0未支付',
  create_time timestamp not null default current_timestamp comment '创建时间',
  update_time timestamp not null default current_timestamp on update current_timestamp comment '修改时间',
  primary key (order_id),
  key idx_buyer_openid (buyer_openid) #把买家微信号作为索引
)comment '订单表';

create table order_detail(
  detail_id varchar(32) not null comment '订单详情id',
  order_id varchar(32) not null comment '订单id',
   product_id          varchar(32)   not null comment '商品id',
   product_name        varchar(64)   not null
  comment '商品名称',
  product_price       decimal(8, 2) not null
  comment '商品单价',
  product_quantity int not null comment '商品数量',
  product_icon        varchar(512)  not null
  comment '商品图标',
  create_time timestamp not null default current_timestamp comment '创建时间',
  update_time timestamp not null default current_timestamp on update current_timestamp comment '修改时间',
  primary key (detail_id),
  key idx_order_id (order_id) #把订单id作为索引 idx代表索引
)comment '订单详情表'