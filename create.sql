
    create table bet (
       bet_id bigint not null auto_increment,
        dt_creation datetime not null,
        numbers varchar(255) not null,
        primary key (bet_id)
    ) type=MyISAM

    create table bet_configuration (
       key_id varchar(255) not null,
        dt_creation datetime,
        dt_update datetime,
        value integer,
        primary key (key_id)
    ) type=MyISAM

    create table bet_type (
       bet_type_id bigint not null auto_increment,
        bet_name varchar(255) not null,
        key_id varchar(255) not null,
        primary key (bet_type_id)
    ) type=MyISAM

    create table bet_user (
       bet_per_user_id bigint not null auto_increment,
        bet_id bigint not null,
        user_id bigint not null,
        primary key (bet_per_user_id)
    ) type=MyISAM

    create table user (
       user_id bigint not null auto_increment,
        birth_date date not null,
        cpf varchar(255) not null,
        email varchar(255) not null,
        name varchar(255) not null,
        primary key (user_id)
    ) type=MyISAM

    alter table user 
       add constraint UK_2qv8vmk5wxu215bevli5derq unique (cpf)

    alter table user 
       add constraint UK_ob8kqyqqgmefl0aco34akdtpe unique (email)

    alter table bet_type 
       add constraint FKmdjjptapt202rgu60qmoesu56 
       foreign key (key_id) 
       references bet_configuration (key_id)

    alter table bet_user 
       add constraint FK77l4kbohk0x0t6y28yr3jixxf 
       foreign key (bet_id) 
       references bet (bet_id)

    alter table bet_user 
       add constraint FKg7lib8c0b1cdf0xrtx3lnka8n 
       foreign key (user_id) 
       references user (user_id)

    create table bet (
       bet_id bigint not null auto_increment,
        dt_creation datetime not null,
        numbers varchar(255) not null,
        primary key (bet_id)
    ) type=MyISAM

    create table bet_configuration (
       key_id varchar(255) not null,
        dt_creation datetime,
        dt_update datetime,
        value integer,
        primary key (key_id)
    ) type=MyISAM

    create table bet_type (
       bet_type_id bigint not null auto_increment,
        bet_name varchar(255) not null,
        key_id varchar(255) not null,
        primary key (bet_type_id)
    ) type=MyISAM

    create table bet_user (
       bet_per_user_id bigint not null auto_increment,
        bet_id bigint not null,
        user_id bigint not null,
        primary key (bet_per_user_id)
    ) type=MyISAM

    create table user (
       user_id bigint not null auto_increment,
        birth_date date not null,
        cpf varchar(255) not null,
        email varchar(255) not null,
        name varchar(255) not null,
        primary key (user_id)
    ) type=MyISAM

    alter table user 
       add constraint UK_2qv8vmk5wxu215bevli5derq unique (cpf)

    alter table user 
       add constraint UK_ob8kqyqqgmefl0aco34akdtpe unique (email)

    alter table bet_type 
       add constraint FKmdjjptapt202rgu60qmoesu56 
       foreign key (key_id) 
       references bet_configuration (key_id)

    alter table bet_user 
       add constraint FK77l4kbohk0x0t6y28yr3jixxf 
       foreign key (bet_id) 
       references bet (bet_id)

    alter table bet_user 
       add constraint FKg7lib8c0b1cdf0xrtx3lnka8n 
       foreign key (user_id) 
       references user (user_id)

    create table bet (
       bet_id bigint not null auto_increment,
        dt_creation datetime not null,
        numbers varchar(255) not null,
        primary key (bet_id)
    ) type=MyISAM

    create table bet_configuration (
       key_id varchar(255) not null,
        dt_creation datetime,
        dt_update datetime,
        value integer,
        primary key (key_id)
    ) type=MyISAM

    create table bet_type (
       bet_type_id bigint not null auto_increment,
        bet_name varchar(255) not null,
        key_id varchar(255) not null,
        primary key (bet_type_id)
    ) type=MyISAM

    create table bet_user (
       bet_per_user_id bigint not null auto_increment,
        bet_id bigint not null,
        user_id bigint not null,
        primary key (bet_per_user_id)
    ) type=MyISAM

    create table user (
       user_id bigint not null auto_increment,
        birth_date date not null,
        cpf varchar(255) not null,
        email varchar(255) not null,
        name varchar(255) not null,
        primary key (user_id)
    ) type=MyISAM

    alter table user 
       add constraint UK_2qv8vmk5wxu215bevli5derq unique (cpf)

    alter table user 
       add constraint UK_ob8kqyqqgmefl0aco34akdtpe unique (email)

    alter table bet_type 
       add constraint FKmdjjptapt202rgu60qmoesu56 
       foreign key (key_id) 
       references bet_configuration (key_id)

    alter table bet_user 
       add constraint FK77l4kbohk0x0t6y28yr3jixxf 
       foreign key (bet_id) 
       references bet (bet_id)

    alter table bet_user 
       add constraint FKg7lib8c0b1cdf0xrtx3lnka8n 
       foreign key (user_id) 
       references user (user_id)

    create table bet (
       bet_id bigint not null auto_increment,
        dt_creation datetime not null,
        numbers varchar(255) not null,
        primary key (bet_id)
    ) type=MyISAM

    create table bet_configuration (
       key_id varchar(255) not null,
        dt_creation datetime,
        dt_update datetime,
        value integer,
        primary key (key_id)
    ) type=MyISAM

    create table bet_type (
       bet_type_id bigint not null auto_increment,
        bet_name varchar(255) not null,
        key_id varchar(255) not null,
        primary key (bet_type_id)
    ) type=MyISAM

    create table bet_user (
       bet_per_user_id bigint not null auto_increment,
        bet_id bigint not null,
        user_id bigint not null,
        primary key (bet_per_user_id)
    ) type=MyISAM

    create table user (
       user_id bigint not null auto_increment,
        birth_date date not null,
        cpf varchar(255) not null,
        email varchar(255) not null,
        name varchar(255) not null,
        primary key (user_id)
    ) type=MyISAM

    alter table user 
       add constraint UK_2qv8vmk5wxu215bevli5derq unique (cpf)

    alter table user 
       add constraint UK_ob8kqyqqgmefl0aco34akdtpe unique (email)

    alter table bet_type 
       add constraint FKmdjjptapt202rgu60qmoesu56 
       foreign key (key_id) 
       references bet_configuration (key_id)

    alter table bet_user 
       add constraint FK77l4kbohk0x0t6y28yr3jixxf 
       foreign key (bet_id) 
       references bet (bet_id)

    alter table bet_user 
       add constraint FKg7lib8c0b1cdf0xrtx3lnka8n 
       foreign key (user_id) 
       references user (user_id)

    create table bet (
       bet_id bigint not null auto_increment,
        dt_creation datetime not null,
        numbers varchar(255) not null,
        primary key (bet_id)
    ) type=MyISAM

    create table bet_configuration (
       key_id varchar(255) not null,
        dt_creation datetime,
        dt_update datetime,
        value integer,
        primary key (key_id)
    ) type=MyISAM

    create table bet_type (
       bet_type_id bigint not null auto_increment,
        bet_name varchar(255) not null,
        key_id varchar(255) not null,
        primary key (bet_type_id)
    ) type=MyISAM

    create table bet_user (
       bet_per_user_id bigint not null auto_increment,
        bet_id bigint not null,
        user_id bigint not null,
        primary key (bet_per_user_id)
    ) type=MyISAM

    create table user (
       user_id bigint not null auto_increment,
        birth_date date not null,
        cpf varchar(255) not null,
        email varchar(255) not null,
        name varchar(255) not null,
        primary key (user_id)
    ) type=MyISAM

    alter table user 
       add constraint UK_2qv8vmk5wxu215bevli5derq unique (cpf)

    alter table user 
       add constraint UK_ob8kqyqqgmefl0aco34akdtpe unique (email)

    alter table bet_type 
       add constraint FKmdjjptapt202rgu60qmoesu56 
       foreign key (key_id) 
       references bet_configuration (key_id)

    alter table bet_user 
       add constraint FK77l4kbohk0x0t6y28yr3jixxf 
       foreign key (bet_id) 
       references bet (bet_id)

    alter table bet_user 
       add constraint FKg7lib8c0b1cdf0xrtx3lnka8n 
       foreign key (user_id) 
       references user (user_id)

    create table bet (
       bet_id bigint not null auto_increment,
        dt_creation datetime not null,
        numbers varchar(255) not null,
        primary key (bet_id)
    ) type=MyISAM

    create table bet_configuration (
       key_id varchar(255) not null,
        dt_creation datetime,
        dt_update datetime,
        value integer,
        primary key (key_id)
    ) type=MyISAM

    create table bet_type (
       bet_type_id bigint not null auto_increment,
        bet_name varchar(255) not null,
        key_id varchar(255) not null,
        primary key (bet_type_id)
    ) type=MyISAM

    create table bet_user (
       bet_per_user_id bigint not null auto_increment,
        bet_id bigint not null,
        user_id bigint not null,
        primary key (bet_per_user_id)
    ) type=MyISAM

    create table user (
       user_id bigint not null auto_increment,
        birth_date date not null,
        cpf varchar(255) not null,
        email varchar(255) not null,
        name varchar(255) not null,
        primary key (user_id)
    ) type=MyISAM

    alter table user 
       add constraint UK_2qv8vmk5wxu215bevli5derq unique (cpf)

    alter table user 
       add constraint UK_ob8kqyqqgmefl0aco34akdtpe unique (email)

    alter table bet_type 
       add constraint FKmdjjptapt202rgu60qmoesu56 
       foreign key (key_id) 
       references bet_configuration (key_id)

    alter table bet_user 
       add constraint FK77l4kbohk0x0t6y28yr3jixxf 
       foreign key (bet_id) 
       references bet (bet_id)

    alter table bet_user 
       add constraint FKg7lib8c0b1cdf0xrtx3lnka8n 
       foreign key (user_id) 
       references user (user_id)

    create table bet (
       bet_id bigint not null auto_increment,
        dt_creation datetime not null,
        numbers varchar(255) not null,
        primary key (bet_id)
    ) type=MyISAM

    create table bet_configuration (
       key_id varchar(255) not null,
        dt_creation datetime,
        dt_update datetime,
        value integer,
        primary key (key_id)
    ) type=MyISAM

    create table bet_type (
       bet_type_id bigint not null auto_increment,
        bet_name varchar(255) not null,
        key_id varchar(255) not null,
        primary key (bet_type_id)
    ) type=MyISAM

    create table bet_user (
       bet_per_user_id bigint not null auto_increment,
        bet_id bigint not null,
        user_id bigint not null,
        primary key (bet_per_user_id)
    ) type=MyISAM

    create table user (
       user_id bigint not null auto_increment,
        birth_date date not null,
        cpf varchar(255) not null,
        email varchar(255) not null,
        name varchar(255) not null,
        primary key (user_id)
    ) type=MyISAM

    alter table user 
       add constraint UK_2qv8vmk5wxu215bevli5derq unique (cpf)

    alter table user 
       add constraint UK_ob8kqyqqgmefl0aco34akdtpe unique (email)

    alter table bet_type 
       add constraint FKmdjjptapt202rgu60qmoesu56 
       foreign key (key_id) 
       references bet_configuration (key_id)

    alter table bet_user 
       add constraint FK77l4kbohk0x0t6y28yr3jixxf 
       foreign key (bet_id) 
       references bet (bet_id)

    alter table bet_user 
       add constraint FKg7lib8c0b1cdf0xrtx3lnka8n 
       foreign key (user_id) 
       references user (user_id)

    create table bet (
       bet_id bigint not null auto_increment,
        dt_creation datetime not null,
        numbers varchar(255) not null,
        primary key (bet_id)
    ) type=MyISAM

    create table bet_configuration (
       key_id varchar(255) not null,
        dt_creation datetime,
        dt_update datetime,
        value integer,
        primary key (key_id)
    ) type=MyISAM

    create table bet_type (
       bet_type_id bigint not null auto_increment,
        bet_name varchar(255) not null,
        key_id varchar(255) not null,
        primary key (bet_type_id)
    ) type=MyISAM

    create table bet_user (
       bet_per_user_id bigint not null auto_increment,
        bet_id bigint not null,
        user_id bigint not null,
        primary key (bet_per_user_id)
    ) type=MyISAM

    create table user (
       user_id bigint not null auto_increment,
        birth_date date not null,
        cpf varchar(255) not null,
        email varchar(255) not null,
        name varchar(255) not null,
        primary key (user_id)
    ) type=MyISAM

    alter table user 
       add constraint UK_2qv8vmk5wxu215bevli5derq unique (cpf)

    alter table user 
       add constraint UK_ob8kqyqqgmefl0aco34akdtpe unique (email)

    alter table bet_type 
       add constraint FKmdjjptapt202rgu60qmoesu56 
       foreign key (key_id) 
       references bet_configuration (key_id)

    alter table bet_user 
       add constraint FK77l4kbohk0x0t6y28yr3jixxf 
       foreign key (bet_id) 
       references bet (bet_id)

    alter table bet_user 
       add constraint FKg7lib8c0b1cdf0xrtx3lnka8n 
       foreign key (user_id) 
       references user (user_id)

    create table bet (
       bet_id bigint not null auto_increment,
        dt_creation datetime not null,
        numbers varchar(255) not null,
        primary key (bet_id)
    ) type=MyISAM

    create table bet_configuration (
       key_id varchar(255) not null,
        dt_creation datetime,
        dt_update datetime,
        value integer,
        primary key (key_id)
    ) type=MyISAM

    create table bet_type (
       bet_type_id bigint not null auto_increment,
        bet_name varchar(255) not null,
        key_id varchar(255) not null,
        primary key (bet_type_id)
    ) type=MyISAM

    create table bet_user (
       bet_per_user_id bigint not null auto_increment,
        bet_id bigint not null,
        user_id bigint not null,
        primary key (bet_per_user_id)
    ) type=MyISAM

    create table user (
       user_id bigint not null auto_increment,
        birth_date date not null,
        cpf varchar(255) not null,
        email varchar(255) not null,
        name varchar(255) not null,
        primary key (user_id)
    ) type=MyISAM

    alter table user 
       add constraint UK_2qv8vmk5wxu215bevli5derq unique (cpf)

    alter table user 
       add constraint UK_ob8kqyqqgmefl0aco34akdtpe unique (email)

    alter table bet_type 
       add constraint FKmdjjptapt202rgu60qmoesu56 
       foreign key (key_id) 
       references bet_configuration (key_id)

    alter table bet_user 
       add constraint FK77l4kbohk0x0t6y28yr3jixxf 
       foreign key (bet_id) 
       references bet (bet_id)

    alter table bet_user 
       add constraint FKg7lib8c0b1cdf0xrtx3lnka8n 
       foreign key (user_id) 
       references user (user_id)

    create table bet (
       bet_id bigint not null auto_increment,
        dt_creation datetime not null,
        numbers varchar(255) not null,
        primary key (bet_id)
    ) type=MyISAM

    create table bet_configuration (
       key_id varchar(255) not null,
        dt_creation datetime,
        dt_update datetime,
        value integer,
        primary key (key_id)
    ) type=MyISAM

    create table bet_type (
       bet_type_id bigint not null auto_increment,
        bet_name varchar(255) not null,
        key_id varchar(255) not null,
        primary key (bet_type_id)
    ) type=MyISAM

    create table bet_user (
       bet_per_user_id bigint not null auto_increment,
        bet_id bigint not null,
        user_id bigint not null,
        primary key (bet_per_user_id)
    ) type=MyISAM

    create table user (
       user_id bigint not null auto_increment,
        birth_date date not null,
        cpf varchar(255) not null,
        email varchar(255) not null,
        name varchar(255) not null,
        primary key (user_id)
    ) type=MyISAM

    alter table user 
       add constraint UK_2qv8vmk5wxu215bevli5derq unique (cpf)

    alter table user 
       add constraint UK_ob8kqyqqgmefl0aco34akdtpe unique (email)

    alter table bet_type 
       add constraint FKmdjjptapt202rgu60qmoesu56 
       foreign key (key_id) 
       references bet_configuration (key_id)

    alter table bet_user 
       add constraint FK77l4kbohk0x0t6y28yr3jixxf 
       foreign key (bet_id) 
       references bet (bet_id)

    alter table bet_user 
       add constraint FKg7lib8c0b1cdf0xrtx3lnka8n 
       foreign key (user_id) 
       references user (user_id)

    create table bet (
       bet_id bigint not null auto_increment,
        dt_creation datetime not null,
        numbers varchar(255) not null,
        primary key (bet_id)
    ) type=MyISAM

    create table bet_configuration (
       key_id varchar(255) not null,
        dt_creation datetime,
        dt_update datetime,
        value integer,
        primary key (key_id)
    ) type=MyISAM

    create table bet_type (
       bet_type_id bigint not null auto_increment,
        bet_name varchar(255) not null,
        key_id varchar(255) not null,
        primary key (bet_type_id)
    ) type=MyISAM

    create table bet_user (
       bet_per_user_id bigint not null auto_increment,
        bet_id bigint not null,
        user_id bigint not null,
        primary key (bet_per_user_id)
    ) type=MyISAM

    create table user (
       user_id bigint not null auto_increment,
        birth_date date not null,
        cpf varchar(255) not null,
        email varchar(255) not null,
        name varchar(255) not null,
        primary key (user_id)
    ) type=MyISAM

    alter table user 
       add constraint UK_2qv8vmk5wxu215bevli5derq unique (cpf)

    alter table user 
       add constraint UK_ob8kqyqqgmefl0aco34akdtpe unique (email)

    alter table bet_type 
       add constraint FKmdjjptapt202rgu60qmoesu56 
       foreign key (key_id) 
       references bet_configuration (key_id)

    alter table bet_user 
       add constraint FK77l4kbohk0x0t6y28yr3jixxf 
       foreign key (bet_id) 
       references bet (bet_id)

    alter table bet_user 
       add constraint FKg7lib8c0b1cdf0xrtx3lnka8n 
       foreign key (user_id) 
       references user (user_id)

    create table bet (
       bet_id bigint not null auto_increment,
        dt_creation datetime not null,
        numbers varchar(255) not null,
        primary key (bet_id)
    ) engine=MyISAM

    create table bet_configuration (
       key_id varchar(255) not null,
        dt_creation datetime,
        dt_update datetime,
        value integer,
        primary key (key_id)
    ) engine=MyISAM

    create table bet_type (
       bet_type_id bigint not null auto_increment,
        bet_name varchar(255) not null,
        key_id varchar(255) not null,
        primary key (bet_type_id)
    ) engine=MyISAM

    create table bet_user (
       bet_per_user_id bigint not null auto_increment,
        bet_id bigint not null,
        user_id bigint not null,
        primary key (bet_per_user_id)
    ) engine=MyISAM

    create table user (
       user_id bigint not null auto_increment,
        birth_date date not null,
        cpf varchar(255) not null,
        email varchar(255) not null,
        name varchar(255) not null,
        primary key (user_id)
    ) engine=MyISAM

    alter table user 
       add constraint UK_2qv8vmk5wxu215bevli5derq unique (cpf)

    alter table user 
       add constraint UK_ob8kqyqqgmefl0aco34akdtpe unique (email)

    alter table bet_type 
       add constraint FKmdjjptapt202rgu60qmoesu56 
       foreign key (key_id) 
       references bet_configuration (key_id)

    alter table bet_user 
       add constraint FK77l4kbohk0x0t6y28yr3jixxf 
       foreign key (bet_id) 
       references bet (bet_id)

    alter table bet_user 
       add constraint FKg7lib8c0b1cdf0xrtx3lnka8n 
       foreign key (user_id) 
       references user (user_id)

    create table bet (
       bet_id bigint not null auto_increment,
        dt_creation datetime not null,
        numbers varchar(255) not null,
        primary key (bet_id)
    ) engine=InnoDB

    create table bet_configuration (
       key_id varchar(255) not null,
        dt_creation datetime,
        dt_update datetime,
        value integer,
        primary key (key_id)
    ) engine=InnoDB

    create table bet_type (
       bet_type_id bigint not null auto_increment,
        bet_name varchar(255) not null,
        key_id varchar(255) not null,
        primary key (bet_type_id)
    ) engine=InnoDB

    create table bet_user (
       bet_per_user_id bigint not null auto_increment,
        bet_id bigint not null,
        user_id bigint not null,
        primary key (bet_per_user_id)
    ) engine=InnoDB

    create table user (
       user_id bigint not null auto_increment,
        birth_date date not null,
        cpf varchar(255) not null,
        email varchar(255) not null,
        name varchar(255) not null,
        primary key (user_id)
    ) engine=InnoDB

    alter table user 
       add constraint UK_2qv8vmk5wxu215bevli5derq unique (cpf)

    alter table user 
       add constraint UK_ob8kqyqqgmefl0aco34akdtpe unique (email)

    alter table bet_type 
       add constraint FKmdjjptapt202rgu60qmoesu56 
       foreign key (key_id) 
       references bet_configuration (key_id)

    alter table bet_user 
       add constraint FK77l4kbohk0x0t6y28yr3jixxf 
       foreign key (bet_id) 
       references bet (bet_id)

    alter table bet_user 
       add constraint FKg7lib8c0b1cdf0xrtx3lnka8n 
       foreign key (user_id) 
       references user (user_id)

    create table bet (
       bet_id bigint not null auto_increment,
        dt_creation datetime not null,
        numbers varchar(255) not null,
        primary key (bet_id)
    ) engine=InnoDB

    create table bet_configuration (
       key_id varchar(255) not null,
        dt_creation datetime,
        dt_update datetime,
        value integer,
        primary key (key_id)
    ) engine=InnoDB

    create table bet_type (
       bet_type_id bigint not null auto_increment,
        bet_name varchar(255) not null,
        key_id varchar(255) not null,
        primary key (bet_type_id)
    ) engine=InnoDB

    create table bet_user (
       bet_per_user_id bigint not null auto_increment,
        bet_id bigint not null,
        user_id bigint not null,
        primary key (bet_per_user_id)
    ) engine=InnoDB

    create table user (
       user_id bigint not null auto_increment,
        birth_date date not null,
        cpf varchar(255) not null,
        email varchar(255) not null,
        name varchar(255) not null,
        primary key (user_id)
    ) engine=InnoDB

    alter table user 
       add constraint UK_2qv8vmk5wxu215bevli5derq unique (cpf)

    alter table user 
       add constraint UK_ob8kqyqqgmefl0aco34akdtpe unique (email)

    alter table bet_type 
       add constraint FKmdjjptapt202rgu60qmoesu56 
       foreign key (key_id) 
       references bet_configuration (key_id)

    alter table bet_user 
       add constraint FK77l4kbohk0x0t6y28yr3jixxf 
       foreign key (bet_id) 
       references bet (bet_id)

    alter table bet_user 
       add constraint FKg7lib8c0b1cdf0xrtx3lnka8n 
       foreign key (user_id) 
       references user (user_id)

    create table bet (
       bet_id bigint not null auto_increment,
        dt_creation datetime not null,
        numbers varchar(255) not null,
        primary key (bet_id)
    ) engine=InnoDB

    create table bet_configuration (
       key_id varchar(255) not null,
        dt_creation datetime,
        dt_update datetime,
        value integer,
        primary key (key_id)
    ) engine=InnoDB

    create table bet_type (
       bet_type_id bigint not null auto_increment,
        bet_name varchar(255) not null,
        key_id varchar(255) not null,
        primary key (bet_type_id)
    ) engine=InnoDB

    create table bet_user (
       bet_per_user_id bigint not null auto_increment,
        bet_id bigint not null,
        user_id bigint not null,
        primary key (bet_per_user_id)
    ) engine=InnoDB

    create table user (
       user_id bigint not null auto_increment,
        birth_date date not null,
        cpf varchar(255) not null,
        email varchar(255) not null,
        name varchar(255) not null,
        primary key (user_id)
    ) engine=InnoDB

    alter table user 
       add constraint UK_2qv8vmk5wxu215bevli5derq unique (cpf)

    alter table user 
       add constraint UK_ob8kqyqqgmefl0aco34akdtpe unique (email)

    alter table bet_type 
       add constraint FKmdjjptapt202rgu60qmoesu56 
       foreign key (key_id) 
       references bet_configuration (key_id)

    alter table bet_user 
       add constraint FK77l4kbohk0x0t6y28yr3jixxf 
       foreign key (bet_id) 
       references bet (bet_id)

    alter table bet_user 
       add constraint FKg7lib8c0b1cdf0xrtx3lnka8n 
       foreign key (user_id) 
       references user (user_id)

    create table bet (
       bet_id bigint not null auto_increment,
        dt_creation datetime not null,
        numbers varchar(255) not null,
        primary key (bet_id)
    ) engine=InnoDB

    create table bet_configuration (
       key_id varchar(255) not null,
        dt_creation datetime,
        dt_update datetime,
        value integer,
        primary key (key_id)
    ) engine=InnoDB

    create table bet_type (
       bet_type_id bigint not null auto_increment,
        bet_name varchar(255) not null,
        key_id varchar(255) not null,
        primary key (bet_type_id)
    ) engine=InnoDB

    create table bet_user (
       bet_per_user_id bigint not null auto_increment,
        bet_id bigint not null,
        user_id bigint not null,
        primary key (bet_per_user_id)
    ) engine=InnoDB

    create table user (
       user_id bigint not null auto_increment,
        birth_date date not null,
        cpf varchar(255) not null,
        email varchar(255) not null,
        name varchar(255) not null,
        primary key (user_id)
    ) engine=InnoDB

    alter table user 
       add constraint UK_2qv8vmk5wxu215bevli5derq unique (cpf)

    alter table user 
       add constraint UK_ob8kqyqqgmefl0aco34akdtpe unique (email)

    alter table bet_type 
       add constraint FKmdjjptapt202rgu60qmoesu56 
       foreign key (key_id) 
       references bet_configuration (key_id)

    alter table bet_user 
       add constraint FK77l4kbohk0x0t6y28yr3jixxf 
       foreign key (bet_id) 
       references bet (bet_id)

    alter table bet_user 
       add constraint FKg7lib8c0b1cdf0xrtx3lnka8n 
       foreign key (user_id) 
       references user (user_id)

    create table bet (
       bet_id bigint not null auto_increment,
        dt_creation datetime not null,
        numbers varchar(255) not null,
        primary key (bet_id)
    ) engine=InnoDB

    create table bet_configuration (
       key_id varchar(255) not null,
        dt_creation datetime,
        dt_update datetime,
        value integer,
        primary key (key_id)
    ) engine=InnoDB

    create table bet_type (
       bet_type_id bigint not null auto_increment,
        bet_name varchar(255) not null,
        key_id varchar(255) not null,
        primary key (bet_type_id)
    ) engine=InnoDB

    create table bet_user (
       bet_per_user_id bigint not null auto_increment,
        bet_id bigint not null,
        user_id bigint not null,
        primary key (bet_per_user_id)
    ) engine=InnoDB

    create table user (
       user_id bigint not null auto_increment,
        birth_date date not null,
        cpf varchar(255) not null,
        email varchar(255) not null,
        name varchar(255) not null,
        primary key (user_id)
    ) engine=InnoDB

    alter table user 
       add constraint UK_2qv8vmk5wxu215bevli5derq unique (cpf)

    alter table user 
       add constraint UK_ob8kqyqqgmefl0aco34akdtpe unique (email)

    alter table bet_type 
       add constraint FKmdjjptapt202rgu60qmoesu56 
       foreign key (key_id) 
       references bet_configuration (key_id)

    alter table bet_user 
       add constraint FK77l4kbohk0x0t6y28yr3jixxf 
       foreign key (bet_id) 
       references bet (bet_id)

    alter table bet_user 
       add constraint FKg7lib8c0b1cdf0xrtx3lnka8n 
       foreign key (user_id) 
       references user (user_id)

    create table bet (
       bet_id bigint not null auto_increment,
        dt_creation datetime not null,
        numbers varchar(255) not null,
        primary key (bet_id)
    ) engine=MyISAM

    create table bet_configuration (
       key_id varchar(255) not null,
        dt_creation datetime,
        dt_update datetime,
        value integer,
        primary key (key_id)
    ) engine=MyISAM

    create table bet_type (
       bet_type_id bigint not null auto_increment,
        bet_name varchar(255) not null,
        key_id varchar(255) not null,
        primary key (bet_type_id)
    ) engine=MyISAM

    create table bet_user (
       bet_per_user_id bigint not null auto_increment,
        bet_id bigint not null,
        user_id bigint not null,
        primary key (bet_per_user_id)
    ) engine=MyISAM

    create table user (
       user_id bigint not null auto_increment,
        birthDate date not null,
        cpf varchar(255) not null,
        email varchar(255) not null,
        name varchar(255) not null,
        primary key (user_id)
    ) engine=MyISAM

    alter table user 
       add constraint UK_2qv8vmk5wxu215bevli5derq unique (cpf)

    alter table user 
       add constraint UK_ob8kqyqqgmefl0aco34akdtpe unique (email)

    alter table bet_type 
       add constraint FKmdjjptapt202rgu60qmoesu56 
       foreign key (key_id) 
       references bet_configuration (key_id)

    alter table bet_user 
       add constraint FK77l4kbohk0x0t6y28yr3jixxf 
       foreign key (bet_id) 
       references bet (bet_id)

    alter table bet_user 
       add constraint FKg7lib8c0b1cdf0xrtx3lnka8n 
       foreign key (user_id) 
       references user (user_id)

    create table bet (
       bet_id bigint not null auto_increment,
        dt_creation datetime not null,
        numbers varchar(255) not null,
        primary key (bet_id)
    ) engine=InnoDB

    create table bet_configuration (
       key_id varchar(255) not null,
        dt_creation datetime,
        dt_update datetime,
        value integer,
        primary key (key_id)
    ) engine=InnoDB

    create table bet_type (
       bet_type_id bigint not null auto_increment,
        bet_name varchar(255) not null,
        key_id varchar(255) not null,
        primary key (bet_type_id)
    ) engine=InnoDB

    create table bet_user (
       bet_per_user_id bigint not null auto_increment,
        bet_id bigint not null,
        user_id bigint not null,
        primary key (bet_per_user_id)
    ) engine=InnoDB

    create table user (
       user_id bigint not null auto_increment,
        birthDate date not null,
        cpf varchar(255) not null,
        email varchar(255) not null,
        name varchar(255) not null,
        primary key (user_id)
    ) engine=InnoDB

    alter table user 
       add constraint UK_2qv8vmk5wxu215bevli5derq unique (cpf)

    alter table user 
       add constraint UK_ob8kqyqqgmefl0aco34akdtpe unique (email)

    alter table bet_type 
       add constraint FKmdjjptapt202rgu60qmoesu56 
       foreign key (key_id) 
       references bet_configuration (key_id)

    alter table bet_user 
       add constraint FK77l4kbohk0x0t6y28yr3jixxf 
       foreign key (bet_id) 
       references bet (bet_id)

    alter table bet_user 
       add constraint FKg7lib8c0b1cdf0xrtx3lnka8n 
       foreign key (user_id) 
       references user (user_id)

    create table bet (
       bet_id bigint not null auto_increment,
        dt_creation datetime not null,
        numbers varchar(255) not null,
        primary key (bet_id)
    ) engine=InnoDB

    create table bet_configuration (
       key_id varchar(255) not null,
        dt_creation datetime,
        dt_update datetime,
        value integer,
        primary key (key_id)
    ) engine=InnoDB

    create table bet_type (
       bet_type_id bigint not null auto_increment,
        bet_name varchar(255) not null,
        key_id varchar(255) not null,
        primary key (bet_type_id)
    ) engine=InnoDB

    create table bet_user (
       bet_per_user_id bigint not null auto_increment,
        bet_id bigint not null,
        user_id bigint not null,
        primary key (bet_per_user_id)
    ) engine=InnoDB

    create table user (
       user_id bigint not null auto_increment,
        birthDate date not null,
        cpf varchar(255) not null,
        email varchar(255) not null,
        name varchar(255) not null,
        primary key (user_id)
    ) engine=InnoDB

    alter table user 
       add constraint UK_2qv8vmk5wxu215bevli5derq unique (cpf)

    alter table user 
       add constraint UK_ob8kqyqqgmefl0aco34akdtpe unique (email)

    alter table bet_type 
       add constraint FKmdjjptapt202rgu60qmoesu56 
       foreign key (key_id) 
       references bet_configuration (key_id)

    alter table bet_user 
       add constraint FK77l4kbohk0x0t6y28yr3jixxf 
       foreign key (bet_id) 
       references bet (bet_id)

    alter table bet_user 
       add constraint FKg7lib8c0b1cdf0xrtx3lnka8n 
       foreign key (user_id) 
       references user (user_id)

    create table bet (
       bet_id bigint not null auto_increment,
        dt_creation datetime not null,
        numbers varchar(255) not null,
        primary key (bet_id)
    ) engine=InnoDB

    create table bet_configuration (
       key_id varchar(255) not null,
        dt_creation datetime,
        dt_update datetime,
        value integer,
        primary key (key_id)
    ) engine=InnoDB

    create table bet_type (
       bet_type_id bigint not null auto_increment,
        bet_name varchar(255) not null,
        key_id varchar(255) not null,
        primary key (bet_type_id)
    ) engine=InnoDB

    create table bet_user (
       bet_per_user_id bigint not null auto_increment,
        bet_id bigint not null,
        user_id bigint not null,
        primary key (bet_per_user_id)
    ) engine=InnoDB

    create table user (
       user_id bigint not null auto_increment,
        birth_date date not null,
        cpf varchar(255) not null,
        email varchar(255) not null,
        name varchar(255) not null,
        primary key (user_id)
    ) engine=InnoDB

    alter table user 
       add constraint UK_2qv8vmk5wxu215bevli5derq unique (cpf)

    alter table user 
       add constraint UK_ob8kqyqqgmefl0aco34akdtpe unique (email)

    alter table bet_type 
       add constraint FKmdjjptapt202rgu60qmoesu56 
       foreign key (key_id) 
       references bet_configuration (key_id)

    alter table bet_user 
       add constraint FK77l4kbohk0x0t6y28yr3jixxf 
       foreign key (bet_id) 
       references bet (bet_id)

    alter table bet_user 
       add constraint FKg7lib8c0b1cdf0xrtx3lnka8n 
       foreign key (user_id) 
       references user (user_id)

    create table bet (
       bet_id bigint not null auto_increment,
        dt_creation datetime not null,
        numbers varchar(255) not null,
        primary key (bet_id)
    ) engine=InnoDB

    create table bet_configuration (
       key_id varchar(255) not null,
        dt_creation datetime,
        dt_update datetime,
        value integer,
        primary key (key_id)
    ) engine=InnoDB

    create table bet_type (
       bet_type_id bigint not null auto_increment,
        bet_name varchar(255) not null,
        key_id varchar(255) not null,
        primary key (bet_type_id)
    ) engine=InnoDB

    create table bet_user (
       bet_per_user_id bigint not null auto_increment,
        bet_id bigint not null,
        user_id bigint not null,
        primary key (bet_per_user_id)
    ) engine=InnoDB

    create table user (
       user_id bigint not null auto_increment,
        birth_date date not null,
        cpf varchar(255) not null,
        email varchar(255) not null,
        name varchar(255) not null,
        primary key (user_id)
    ) engine=InnoDB

    alter table user 
       add constraint UK_2qv8vmk5wxu215bevli5derq unique (cpf)

    alter table user 
       add constraint UK_ob8kqyqqgmefl0aco34akdtpe unique (email)

    alter table bet_type 
       add constraint FKmdjjptapt202rgu60qmoesu56 
       foreign key (key_id) 
       references bet_configuration (key_id)

    alter table bet_user 
       add constraint FK77l4kbohk0x0t6y28yr3jixxf 
       foreign key (bet_id) 
       references bet (bet_id)

    alter table bet_user 
       add constraint FKg7lib8c0b1cdf0xrtx3lnka8n 
       foreign key (user_id) 
       references user (user_id)

    create table bet (
       bet_id bigint not null auto_increment,
        dt_creation datetime not null,
        numbers varchar(255) not null,
        primary key (bet_id)
    ) engine=InnoDB

    create table bet_configuration (
       key_id varchar(255) not null,
        dt_creation datetime,
        dt_update datetime,
        value integer,
        primary key (key_id)
    ) engine=InnoDB

    create table bet_type (
       bet_type_id bigint not null auto_increment,
        bet_name varchar(255) not null,
        key_id varchar(255) not null,
        primary key (bet_type_id)
    ) engine=InnoDB

    create table bet_user (
       bet_per_user_id bigint not null auto_increment,
        bet_id bigint not null,
        user_id bigint not null,
        primary key (bet_per_user_id)
    ) engine=InnoDB

    create table user (
       user_id bigint not null auto_increment,
        birth_date date not null,
        cpf varchar(255) not null,
        email varchar(255) not null,
        name varchar(255) not null,
        primary key (user_id)
    ) engine=InnoDB

    alter table user 
       add constraint UK_2qv8vmk5wxu215bevli5derq unique (cpf)

    alter table user 
       add constraint UK_ob8kqyqqgmefl0aco34akdtpe unique (email)

    alter table bet_type 
       add constraint FKmdjjptapt202rgu60qmoesu56 
       foreign key (key_id) 
       references bet_configuration (key_id)

    alter table bet_user 
       add constraint FK77l4kbohk0x0t6y28yr3jixxf 
       foreign key (bet_id) 
       references bet (bet_id)

    alter table bet_user 
       add constraint FKg7lib8c0b1cdf0xrtx3lnka8n 
       foreign key (user_id) 
       references user (user_id)

    create table bet (
       bet_id bigint not null auto_increment,
        dt_creation datetime not null,
        numbers varchar(255) not null,
        primary key (bet_id)
    ) engine=InnoDB

    create table bet_configuration (
       key_id varchar(255) not null,
        dt_creation datetime,
        dt_update datetime,
        value integer,
        primary key (key_id)
    ) engine=InnoDB

    create table bet_type (
       bet_type_id bigint not null auto_increment,
        bet_name varchar(255) not null,
        key_id varchar(255) not null,
        primary key (bet_type_id)
    ) engine=InnoDB

    create table bet_user (
       bet_per_user_id bigint not null auto_increment,
        bet_id bigint not null,
        user_id bigint not null,
        primary key (bet_per_user_id)
    ) engine=InnoDB

    create table user (
       user_id bigint not null auto_increment,
        birth_date date not null,
        cpf varchar(255) not null,
        email varchar(255) not null,
        name varchar(255) not null,
        primary key (user_id)
    ) engine=InnoDB

    alter table user 
       add constraint UK_2qv8vmk5wxu215bevli5derq unique (cpf)

    alter table user 
       add constraint UK_ob8kqyqqgmefl0aco34akdtpe unique (email)

    alter table bet_type 
       add constraint FKmdjjptapt202rgu60qmoesu56 
       foreign key (key_id) 
       references bet_configuration (key_id)

    alter table bet_user 
       add constraint FK77l4kbohk0x0t6y28yr3jixxf 
       foreign key (bet_id) 
       references bet (bet_id)

    alter table bet_user 
       add constraint FKg7lib8c0b1cdf0xrtx3lnka8n 
       foreign key (user_id) 
       references user (user_id)

    create table bet (
       bet_id bigint not null auto_increment,
        dt_creation datetime not null,
        numbers varchar(255) not null,
        primary key (bet_id)
    ) engine=InnoDB

    create table bet_configuration (
       key_id varchar(255) not null,
        dt_creation datetime,
        dt_update datetime,
        value integer,
        primary key (key_id)
    ) engine=InnoDB

    create table bet_type (
       bet_type_id bigint not null auto_increment,
        bet_name varchar(255) not null,
        key_id varchar(255) not null,
        primary key (bet_type_id)
    ) engine=InnoDB

    create table bet_user (
       bet_per_user_id bigint not null auto_increment,
        bet_id bigint not null,
        user_id bigint not null,
        primary key (bet_per_user_id)
    ) engine=InnoDB

    create table user (
       user_id bigint not null auto_increment,
        birth_date date not null,
        cpf varchar(255) not null,
        email varchar(255) not null,
        name varchar(255) not null,
        primary key (user_id)
    ) engine=InnoDB

    alter table user 
       add constraint UK_2qv8vmk5wxu215bevli5derq unique (cpf)

    alter table user 
       add constraint UK_ob8kqyqqgmefl0aco34akdtpe unique (email)

    alter table bet_type 
       add constraint FKmdjjptapt202rgu60qmoesu56 
       foreign key (key_id) 
       references bet_configuration (key_id)

    alter table bet_user 
       add constraint FK77l4kbohk0x0t6y28yr3jixxf 
       foreign key (bet_id) 
       references bet (bet_id)

    alter table bet_user 
       add constraint FKg7lib8c0b1cdf0xrtx3lnka8n 
       foreign key (user_id) 
       references user (user_id)
