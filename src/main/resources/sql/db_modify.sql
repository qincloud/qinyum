CREATE TABLE sys_dict
(
    id VARCHAR(64) PRIMARY KEY NOT NULL,
    mc VARCHAR(64),
    bm VARCHAR(10),
    zdlb VARCHAR(10),
    lbmc VARCHAR(64),
    create_by VARCHAR(64),
    delete_by VARCHAR(64),
    del_flag VARCHAR(1) DEFAULT '0',
    create_time DATETIME,
    delete_time DATETIME
);

CREATE TABLE sys_org
(
    id VARCHAR(64) PRIMARY KEY NOT NULL,
    mc VARCHAR(64),
    bm VARCHAR(64),
    fmc VARCHAR(64),
    fbm VARCHAR(64),
    create_by VARCHAR(64),
    delete_by VARCHAR(64),
    del_flag VARCHAR(1) DEFAULT '0',
    create_time DATETIME,
    delete_time DATETIME
);