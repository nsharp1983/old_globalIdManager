delete from relationship_type_dict;
delete from nationality_dict;
delete from degree_type_dict;
delete from marital_status_dict;
delete from Gender_Dict;
delete from audit_event_type_dict;


insert into relationship_type_dict (RELATIONSHIP_TYPE_CD, RELATIONSHIP_TYPE_CODE, RELATIONSHIP_TYPE_NAME, INPUT_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (1, '1', '夫妻', 'FQ', 'pm', null, null, null, null, null, null, null);

insert into relationship_type_dict (RELATIONSHIP_TYPE_CD, RELATIONSHIP_TYPE_CODE, RELATIONSHIP_TYPE_NAME, INPUT_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (2, '2', '子女', 'ZN', 'pm', null, null, null, null, null, null, null);

insert into relationship_type_dict (RELATIONSHIP_TYPE_CD, RELATIONSHIP_TYPE_CODE, RELATIONSHIP_TYPE_NAME, INPUT_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (3, '3', '(外)孙子女', 'WSZN', 'pm', null, null, null, null, null, null, null);

insert into relationship_type_dict (RELATIONSHIP_TYPE_CD, RELATIONSHIP_TYPE_CODE, RELATIONSHIP_TYPE_NAME, INPUT_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (4, '4', '(岳)父母', 'YFM', 'pm', null, null, null, null, null, null, null);

insert into relationship_type_dict (RELATIONSHIP_TYPE_CD, RELATIONSHIP_TYPE_CODE, RELATIONSHIP_TYPE_NAME, INPUT_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (5, '5', '(外)祖父母', 'WZFM', 'pm', null, null, null, null, null, null, null);

insert into relationship_type_dict (RELATIONSHIP_TYPE_CD, RELATIONSHIP_TYPE_CODE, RELATIONSHIP_TYPE_NAME, INPUT_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (6, '6', '兄弟姐妹', 'XDJM', 'pm', null, null, null, null, null, null, null);

insert into relationship_type_dict (RELATIONSHIP_TYPE_CD, RELATIONSHIP_TYPE_CODE, RELATIONSHIP_TYPE_NAME, INPUT_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (7, '7', '亲属', 'QS', 'pm', null, null, null, null, null, null, null);

insert into relationship_type_dict (RELATIONSHIP_TYPE_CD, RELATIONSHIP_TYPE_CODE, RELATIONSHIP_TYPE_NAME, INPUT_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (8, '8', '朋友', 'PY', 'pm', null, null, null, null, null, null, null);

insert into relationship_type_dict (RELATIONSHIP_TYPE_CD, RELATIONSHIP_TYPE_CODE, RELATIONSHIP_TYPE_NAME, INPUT_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (9, '9', '同事', 'TS', 'pm', null, null, null, null, null, null, null);

insert into relationship_type_dict (RELATIONSHIP_TYPE_CD, RELATIONSHIP_TYPE_CODE, RELATIONSHIP_TYPE_NAME, INPUT_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (10, '10', '其他', 'QT', 'pm', null, null, null, null, null, null, null);

insert into relationship_type_dict (RELATIONSHIP_TYPE_CD, RELATIONSHIP_TYPE_CODE, RELATIONSHIP_TYPE_NAME, INPUT_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (11, '11', '父子', 'FZ', 'pm', null, null, null, null, null, null, null);

insert into relationship_type_dict (RELATIONSHIP_TYPE_CD, RELATIONSHIP_TYPE_CODE, RELATIONSHIP_TYPE_NAME, INPUT_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (12, '12', '父女', 'FN', 'pm', null, null, null, null, null, null, null);

insert into relationship_type_dict (RELATIONSHIP_TYPE_CD, RELATIONSHIP_TYPE_CODE, RELATIONSHIP_TYPE_NAME, INPUT_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (13, '13', '母女', 'MN', 'pm', null, null, null, null, null, null, null);

insert into relationship_type_dict (RELATIONSHIP_TYPE_CD, RELATIONSHIP_TYPE_CODE, RELATIONSHIP_TYPE_NAME, INPUT_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (14, '14', '母子', 'MZ', 'pm', null, null, null, null, null, null, null);


insert into nationality_dict (NATIONALITY_CD, NATIONALITY_NAME, NATIONALITY_DESCRIPTION, ATIONALITY_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (110, '毛里塔尼亚', '毛里塔尼亚', '110', null, null, null, null, null, null, null, null);

insert into nationality_dict (NATIONALITY_CD, NATIONALITY_NAME, NATIONALITY_DESCRIPTION, ATIONALITY_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (111, '毛里求斯', '毛里求斯', '111', null, null, null, null, null, null, null, null);

insert into nationality_dict (NATIONALITY_CD, NATIONALITY_NAME, NATIONALITY_DESCRIPTION, ATIONALITY_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (112, '马拉维', '马拉维', '112', null, null, null, null, null, null, null, null);

insert into nationality_dict (NATIONALITY_CD, NATIONALITY_NAME, NATIONALITY_DESCRIPTION, ATIONALITY_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (113, '马来西亚', '马来西亚', '113', null, null, null, null, null, null, null, null);

insert into nationality_dict (NATIONALITY_CD, NATIONALITY_NAME, NATIONALITY_DESCRIPTION, ATIONALITY_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (114, '纳米比亚', '纳米比亚', '114', null, null, null, null, null, null, null, null);

insert into nationality_dict (NATIONALITY_CD, NATIONALITY_NAME, NATIONALITY_DESCRIPTION, ATIONALITY_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (115, '尼日尔', '尼日尔', '115', null, null, null, null, null, null, null, null);

insert into nationality_dict (NATIONALITY_CD, NATIONALITY_NAME, NATIONALITY_DESCRIPTION, ATIONALITY_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (116, '尼日利亚', '尼日利亚', '116', null, null, null, null, null, null, null, null);

insert into nationality_dict (NATIONALITY_CD, NATIONALITY_NAME, NATIONALITY_DESCRIPTION, ATIONALITY_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (117, '尼加拉瓜', '尼加拉瓜', '117', null, null, null, null, null, null, null, null);

insert into nationality_dict (NATIONALITY_CD, NATIONALITY_NAME, NATIONALITY_DESCRIPTION, ATIONALITY_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (118, '荷兰', '荷兰', '118', null, null, null, null, null, null, null, null);

insert into nationality_dict (NATIONALITY_CD, NATIONALITY_NAME, NATIONALITY_DESCRIPTION, ATIONALITY_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (119, '挪威', '挪威', '119', null, null, null, null, null, null, null, null);

insert into nationality_dict (NATIONALITY_CD, NATIONALITY_NAME, NATIONALITY_DESCRIPTION, ATIONALITY_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (120, '尼泊尔', '尼泊尔', '120', null, null, null, null, null, null, null, null);

insert into nationality_dict (NATIONALITY_CD, NATIONALITY_NAME, NATIONALITY_DESCRIPTION, ATIONALITY_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (121, '新西兰', '新西兰', '121', null, null, null, null, null, null, null, null);

insert into nationality_dict (NATIONALITY_CD, NATIONALITY_NAME, NATIONALITY_DESCRIPTION, ATIONALITY_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (122, '阿曼', '阿曼', '122', null, null, null, null, null, null, null, null);

insert into nationality_dict (NATIONALITY_CD, NATIONALITY_NAME, NATIONALITY_DESCRIPTION, ATIONALITY_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (123, '巴基斯坦', '巴基斯坦', '123', null, null, null, null, null, null, null, null);

insert into nationality_dict (NATIONALITY_CD, NATIONALITY_NAME, NATIONALITY_DESCRIPTION, ATIONALITY_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (124, '巴拿马', '巴拿马', '124', null, null, null, null, null, null, null, null);

insert into nationality_dict (NATIONALITY_CD, NATIONALITY_NAME, NATIONALITY_DESCRIPTION, ATIONALITY_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (125, '秘鲁', '秘鲁', '125', null, null, null, null, null, null, null, null);

insert into nationality_dict (NATIONALITY_CD, NATIONALITY_NAME, NATIONALITY_DESCRIPTION, ATIONALITY_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (126, '菲律宾', '菲律宾', '126', null, null, null, null, null, null, null, null);

insert into nationality_dict (NATIONALITY_CD, NATIONALITY_NAME, NATIONALITY_DESCRIPTION, ATIONALITY_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (127, '巴布亚新几内亚', '巴布亚新几内亚', '127', null, null, null, null, null, null, null, null);

insert into nationality_dict (NATIONALITY_CD, NATIONALITY_NAME, NATIONALITY_DESCRIPTION, ATIONALITY_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (128, '波兰', '波兰', '128', null, null, null, null, null, null, null, null);

insert into nationality_dict (NATIONALITY_CD, NATIONALITY_NAME, NATIONALITY_DESCRIPTION, ATIONALITY_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (129, '波多黎各', '波多黎各', '129', null, null, null, null, null, null, null, null);

insert into nationality_dict (NATIONALITY_CD, NATIONALITY_NAME, NATIONALITY_DESCRIPTION, ATIONALITY_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (130, '朝鲜', '朝鲜', '130', null, null, null, null, null, null, null, null);

insert into nationality_dict (NATIONALITY_CD, NATIONALITY_NAME, NATIONALITY_DESCRIPTION, ATIONALITY_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (131, '葡萄牙', '葡萄牙', '131', null, null, null, null, null, null, null, null);

insert into nationality_dict (NATIONALITY_CD, NATIONALITY_NAME, NATIONALITY_DESCRIPTION, ATIONALITY_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (132, '巴拉圭', '巴拉圭', '132', null, null, null, null, null, null, null, null);

insert into nationality_dict (NATIONALITY_CD, NATIONALITY_NAME, NATIONALITY_DESCRIPTION, ATIONALITY_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (133, '卡塔尔', '卡塔尔', '133', null, null, null, null, null, null, null, null);

insert into nationality_dict (NATIONALITY_CD, NATIONALITY_NAME, NATIONALITY_DESCRIPTION, ATIONALITY_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (134, '留尼旺岛', '留尼旺岛', '134', null, null, null, null, null, null, null, null);

insert into nationality_dict (NATIONALITY_CD, NATIONALITY_NAME, NATIONALITY_DESCRIPTION, ATIONALITY_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (135, '罗马尼亚', '罗马尼亚', '135', null, null, null, null, null, null, null, null);

insert into nationality_dict (NATIONALITY_CD, NATIONALITY_NAME, NATIONALITY_DESCRIPTION, ATIONALITY_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (136, '卢旺达', '卢旺达', '136', null, null, null, null, null, null, null, null);

insert into nationality_dict (NATIONALITY_CD, NATIONALITY_NAME, NATIONALITY_DESCRIPTION, ATIONALITY_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (137, '沙特阿拉泊', '沙特阿拉泊', '137', null, null, null, null, null, null, null, null);

insert into nationality_dict (NATIONALITY_CD, NATIONALITY_NAME, NATIONALITY_DESCRIPTION, ATIONALITY_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (138, '苏丹', '苏丹', '138', null, null, null, null, null, null, null, null);

insert into nationality_dict (NATIONALITY_CD, NATIONALITY_NAME, NATIONALITY_DESCRIPTION, ATIONALITY_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (139, '塞内加尔', '塞内加尔', '139', null, null, null, null, null, null, null, null);

insert into nationality_dict (NATIONALITY_CD, NATIONALITY_NAME, NATIONALITY_DESCRIPTION, ATIONALITY_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (140, '索罗门群岛', '索罗门群岛', '140', null, null, null, null, null, null, null, null);

insert into nationality_dict (NATIONALITY_CD, NATIONALITY_NAME, NATIONALITY_DESCRIPTION, ATIONALITY_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (141, '塞拉利昂', '塞拉利昂', '141', null, null, null, null, null, null, null, null);

insert into nationality_dict (NATIONALITY_CD, NATIONALITY_NAME, NATIONALITY_DESCRIPTION, ATIONALITY_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (142, '萨尔瓦多', '萨尔瓦多', '142', null, null, null, null, null, null, null, null);

insert into nationality_dict (NATIONALITY_CD, NATIONALITY_NAME, NATIONALITY_DESCRIPTION, ATIONALITY_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (143, '圣马力诺', '圣马力诺', '143', null, null, null, null, null, null, null, null);

insert into nationality_dict (NATIONALITY_CD, NATIONALITY_NAME, NATIONALITY_DESCRIPTION, ATIONALITY_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (144, '索马里', '索马里', '144', null, null, null, null, null, null, null, null);

insert into nationality_dict (NATIONALITY_CD, NATIONALITY_NAME, NATIONALITY_DESCRIPTION, ATIONALITY_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (145, '圣多美和普林西比', '圣多美和普林西比', '145', null, null, null, null, null, null, null, null);

insert into nationality_dict (NATIONALITY_CD, NATIONALITY_NAME, NATIONALITY_DESCRIPTION, ATIONALITY_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (146, '苏联', '苏联', '146', null, null, null, null, null, null, null, null);

insert into nationality_dict (NATIONALITY_CD, NATIONALITY_NAME, NATIONALITY_DESCRIPTION, ATIONALITY_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (147, '苏里南', '苏里南', '147', null, null, null, null, null, null, null, null);

insert into nationality_dict (NATIONALITY_CD, NATIONALITY_NAME, NATIONALITY_DESCRIPTION, ATIONALITY_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (1, '中国', '中国', '1', null, null, null, null, null, null, null, null);

insert into nationality_dict (NATIONALITY_CD, NATIONALITY_NAME, NATIONALITY_DESCRIPTION, ATIONALITY_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (4, '美国', '美国', '4', null, null, null, null, null, null, null, null);

insert into nationality_dict (NATIONALITY_CD, NATIONALITY_NAME, NATIONALITY_DESCRIPTION, ATIONALITY_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (5, '日本', '日本', '5', null, null, null, null, null, null, null, null);

insert into nationality_dict (NATIONALITY_CD, NATIONALITY_NAME, NATIONALITY_DESCRIPTION, ATIONALITY_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (6, '加拿大', '加拿大', '6', null, null, null, null, null, null, null, null);

insert into nationality_dict (NATIONALITY_CD, NATIONALITY_NAME, NATIONALITY_DESCRIPTION, ATIONALITY_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (7, '英国', '英国', '7', null, null, null, null, null, null, null, null);

insert into nationality_dict (NATIONALITY_CD, NATIONALITY_NAME, NATIONALITY_DESCRIPTION, ATIONALITY_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (8, '德国', '德国', '8', null, null, null, null, null, null, null, null);

insert into nationality_dict (NATIONALITY_CD, NATIONALITY_NAME, NATIONALITY_DESCRIPTION, ATIONALITY_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (9, '新加坡', '新加坡', '9', null, null, null, null, null, null, null, null);

insert into nationality_dict (NATIONALITY_CD, NATIONALITY_NAME, NATIONALITY_DESCRIPTION, ATIONALITY_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (10, '法国', '法国', '10', null, null, null, null, null, null, null, null);

insert into nationality_dict (NATIONALITY_CD, NATIONALITY_NAME, NATIONALITY_DESCRIPTION, ATIONALITY_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (11, '阿富汗', '阿富汗', '11', null, null, null, null, null, null, null, null);

insert into nationality_dict (NATIONALITY_CD, NATIONALITY_NAME, NATIONALITY_DESCRIPTION, ATIONALITY_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (12, '安哥拉', '安哥拉', '12', null, null, null, null, null, null, null, null);

insert into nationality_dict (NATIONALITY_CD, NATIONALITY_NAME, NATIONALITY_DESCRIPTION, ATIONALITY_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (13, '阿尔巴尼亚', '阿尔巴尼亚', '13', null, null, null, null, null, null, null, null);

insert into nationality_dict (NATIONALITY_CD, NATIONALITY_NAME, NATIONALITY_DESCRIPTION, ATIONALITY_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (14, '安道尔', '安道尔', '14', null, null, null, null, null, null, null, null);

insert into nationality_dict (NATIONALITY_CD, NATIONALITY_NAME, NATIONALITY_DESCRIPTION, ATIONALITY_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (15, '荷安的列斯', '荷安的列斯', '15', null, null, null, null, null, null, null, null);

insert into nationality_dict (NATIONALITY_CD, NATIONALITY_NAME, NATIONALITY_DESCRIPTION, ATIONALITY_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (16, '阿联酋', '阿联酋', '16', null, null, null, null, null, null, null, null);

insert into nationality_dict (NATIONALITY_CD, NATIONALITY_NAME, NATIONALITY_DESCRIPTION, ATIONALITY_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (148, '瑞典', '瑞典', '148', null, null, null, null, null, null, null, null);

insert into nationality_dict (NATIONALITY_CD, NATIONALITY_NAME, NATIONALITY_DESCRIPTION, ATIONALITY_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (149, '塞舌尔', '塞舌尔', '149', null, null, null, null, null, null, null, null);

insert into nationality_dict (NATIONALITY_CD, NATIONALITY_NAME, NATIONALITY_DESCRIPTION, ATIONALITY_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (150, '叙利亚', '叙利亚', '150', null, null, null, null, null, null, null, null);

insert into nationality_dict (NATIONALITY_CD, NATIONALITY_NAME, NATIONALITY_DESCRIPTION, ATIONALITY_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (151, '乍得', '乍得', '151', null, null, null, null, null, null, null, null);

insert into nationality_dict (NATIONALITY_CD, NATIONALITY_NAME, NATIONALITY_DESCRIPTION, ATIONALITY_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (152, '多哥', '多哥', '152', null, null, null, null, null, null, null, null);

insert into nationality_dict (NATIONALITY_CD, NATIONALITY_NAME, NATIONALITY_DESCRIPTION, ATIONALITY_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (153, '泰国', '泰国', '153', null, null, null, null, null, null, null, null);

insert into nationality_dict (NATIONALITY_CD, NATIONALITY_NAME, NATIONALITY_DESCRIPTION, ATIONALITY_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (154, '东帝汶', '东帝汶', '154', null, null, null, null, null, null, null, null);

insert into nationality_dict (NATIONALITY_CD, NATIONALITY_NAME, NATIONALITY_DESCRIPTION, ATIONALITY_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (155, '汤加', '汤加', '155', null, null, null, null, null, null, null, null);

insert into nationality_dict (NATIONALITY_CD, NATIONALITY_NAME, NATIONALITY_DESCRIPTION, ATIONALITY_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (156, '特立尼达和多巴哥', '特立尼达和多巴哥', '156', null, null, null, null, null, null, null, null);

insert into nationality_dict (NATIONALITY_CD, NATIONALITY_NAME, NATIONALITY_DESCRIPTION, ATIONALITY_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (157, '突尼斯', '突尼斯', '157', null, null, null, null, null, null, null, null);

insert into nationality_dict (NATIONALITY_CD, NATIONALITY_NAME, NATIONALITY_DESCRIPTION, ATIONALITY_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (158, '台湾', '台湾', '158', null, null, null, null, null, null, null, null);

insert into nationality_dict (NATIONALITY_CD, NATIONALITY_NAME, NATIONALITY_DESCRIPTION, ATIONALITY_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (159, '土耳其', '土耳其', '159', null, null, null, null, null, null, null, null);

insert into nationality_dict (NATIONALITY_CD, NATIONALITY_NAME, NATIONALITY_DESCRIPTION, ATIONALITY_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (160, '坦桑尼亚', '坦桑尼亚', '160', null, null, null, null, null, null, null, null);

insert into nationality_dict (NATIONALITY_CD, NATIONALITY_NAME, NATIONALITY_DESCRIPTION, ATIONALITY_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (161, '乌干达', '乌干达', '161', null, null, null, null, null, null, null, null);

insert into nationality_dict (NATIONALITY_CD, NATIONALITY_NAME, NATIONALITY_DESCRIPTION, ATIONALITY_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (162, '乌克兰', '乌克兰', '162', null, null, null, null, null, null, null, null);

insert into nationality_dict (NATIONALITY_CD, NATIONALITY_NAME, NATIONALITY_DESCRIPTION, ATIONALITY_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (163, '乌拉圭', '乌拉圭', '163', null, null, null, null, null, null, null, null);

insert into nationality_dict (NATIONALITY_CD, NATIONALITY_NAME, NATIONALITY_DESCRIPTION, ATIONALITY_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (164, '梵帝冈', '梵帝冈', '164', null, null, null, null, null, null, null, null);

insert into nationality_dict (NATIONALITY_CD, NATIONALITY_NAME, NATIONALITY_DESCRIPTION, ATIONALITY_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (165, '委内瑞拉', '委内瑞拉', '165', null, null, null, null, null, null, null, null);

insert into nationality_dict (NATIONALITY_CD, NATIONALITY_NAME, NATIONALITY_DESCRIPTION, ATIONALITY_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (166, '越南', '越南', '166', null, null, null, null, null, null, null, null);

insert into nationality_dict (NATIONALITY_CD, NATIONALITY_NAME, NATIONALITY_DESCRIPTION, ATIONALITY_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (167, '萨摩亚', '萨摩亚', '167', null, null, null, null, null, null, null, null);

insert into nationality_dict (NATIONALITY_CD, NATIONALITY_NAME, NATIONALITY_DESCRIPTION, ATIONALITY_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (168, '也门', '也门', '168', null, null, null, null, null, null, null, null);

insert into nationality_dict (NATIONALITY_CD, NATIONALITY_NAME, NATIONALITY_DESCRIPTION, ATIONALITY_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (169, '南斯拉夫', '南斯拉夫', '169', null, null, null, null, null, null, null, null);

insert into nationality_dict (NATIONALITY_CD, NATIONALITY_NAME, NATIONALITY_DESCRIPTION, ATIONALITY_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (170, '南非', '南非', '170', null, null, null, null, null, null, null, null);

insert into nationality_dict (NATIONALITY_CD, NATIONALITY_NAME, NATIONALITY_DESCRIPTION, ATIONALITY_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (171, '扎伊尔', '扎伊尔', '171', null, null, null, null, null, null, null, null);

insert into nationality_dict (NATIONALITY_CD, NATIONALITY_NAME, NATIONALITY_DESCRIPTION, ATIONALITY_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (172, '赞比亚', '赞比亚', '172', null, null, null, null, null, null, null, null);

insert into nationality_dict (NATIONALITY_CD, NATIONALITY_NAME, NATIONALITY_DESCRIPTION, ATIONALITY_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (173, '津巴布韦', '津巴布韦', '173', null, null, null, null, null, null, null, null);

insert into nationality_dict (NATIONALITY_CD, NATIONALITY_NAME, NATIONALITY_DESCRIPTION, ATIONALITY_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (174, '留尼旺', '留尼旺', '174', null, null, null, null, null, null, null, null);

insert into nationality_dict (NATIONALITY_CD, NATIONALITY_NAME, NATIONALITY_DESCRIPTION, ATIONALITY_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (175, '其它', '其它', '175', null, null, null, null, null, null, null, null);

insert into nationality_dict (NATIONALITY_CD, NATIONALITY_NAME, NATIONALITY_DESCRIPTION, ATIONALITY_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (176, '俄罗斯', '俄罗斯', '176', null, null, null, null, null, null, null, null);

insert into nationality_dict (NATIONALITY_CD, NATIONALITY_NAME, NATIONALITY_DESCRIPTION, ATIONALITY_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (177, '马里', '马里', '177', null, null, null, null, null, null, null, null);

insert into nationality_dict (NATIONALITY_CD, NATIONALITY_NAME, NATIONALITY_DESCRIPTION, ATIONALITY_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (300, '其他', '其他', '300', null, null, null, null, null, null, null, null);

insert into nationality_dict (NATIONALITY_CD, NATIONALITY_NAME, NATIONALITY_DESCRIPTION, ATIONALITY_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (17, '阿根延', '阿根延', '17', null, null, null, null, null, null, null, null);

insert into nationality_dict (NATIONALITY_CD, NATIONALITY_NAME, NATIONALITY_DESCRIPTION, ATIONALITY_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (18, '萨摩亚（美属）', '萨摩亚（美属）', '18', null, null, null, null, null, null, null, null);

insert into nationality_dict (NATIONALITY_CD, NATIONALITY_NAME, NATIONALITY_DESCRIPTION, ATIONALITY_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (19, '澳大利亚', '澳大利亚', '19', null, null, null, null, null, null, null, null);

insert into nationality_dict (NATIONALITY_CD, NATIONALITY_NAME, NATIONALITY_DESCRIPTION, ATIONALITY_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (20, '奥地利', '奥地利', '20', null, null, null, null, null, null, null, null);

insert into nationality_dict (NATIONALITY_CD, NATIONALITY_NAME, NATIONALITY_DESCRIPTION, ATIONALITY_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (21, '布隆迪', '布隆迪', '21', null, null, null, null, null, null, null, null);

insert into nationality_dict (NATIONALITY_CD, NATIONALITY_NAME, NATIONALITY_DESCRIPTION, ATIONALITY_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (22, '比利时', '比利时', '22', null, null, null, null, null, null, null, null);

insert into nationality_dict (NATIONALITY_CD, NATIONALITY_NAME, NATIONALITY_DESCRIPTION, ATIONALITY_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (23, '贝宁', '贝宁', '23', null, null, null, null, null, null, null, null);

insert into nationality_dict (NATIONALITY_CD, NATIONALITY_NAME, NATIONALITY_DESCRIPTION, ATIONALITY_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (24, '孟加拉', '孟加拉', '24', null, null, null, null, null, null, null, null);

insert into nationality_dict (NATIONALITY_CD, NATIONALITY_NAME, NATIONALITY_DESCRIPTION, ATIONALITY_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (25, '保加利亚', '保加利亚', '25', null, null, null, null, null, null, null, null);

insert into nationality_dict (NATIONALITY_CD, NATIONALITY_NAME, NATIONALITY_DESCRIPTION, ATIONALITY_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (26, '巴林', '巴林', '26', null, null, null, null, null, null, null, null);

insert into nationality_dict (NATIONALITY_CD, NATIONALITY_NAME, NATIONALITY_DESCRIPTION, ATIONALITY_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (27, '百慕大', '百慕大', '27', null, null, null, null, null, null, null, null);

insert into nationality_dict (NATIONALITY_CD, NATIONALITY_NAME, NATIONALITY_DESCRIPTION, ATIONALITY_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (28, '玻利维亚', '玻利维亚', '28', null, null, null, null, null, null, null, null);

insert into nationality_dict (NATIONALITY_CD, NATIONALITY_NAME, NATIONALITY_DESCRIPTION, ATIONALITY_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (29, '巴西', '巴西', '29', null, null, null, null, null, null, null, null);

insert into nationality_dict (NATIONALITY_CD, NATIONALITY_NAME, NATIONALITY_DESCRIPTION, ATIONALITY_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (30, '巴巴多斯', '巴巴多斯', '30', null, null, null, null, null, null, null, null);

insert into nationality_dict (NATIONALITY_CD, NATIONALITY_NAME, NATIONALITY_DESCRIPTION, ATIONALITY_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (31, '文莱', '文莱', '31', null, null, null, null, null, null, null, null);

insert into nationality_dict (NATIONALITY_CD, NATIONALITY_NAME, NATIONALITY_DESCRIPTION, ATIONALITY_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (32, '不丹', '不丹', '32', null, null, null, null, null, null, null, null);

insert into nationality_dict (NATIONALITY_CD, NATIONALITY_NAME, NATIONALITY_DESCRIPTION, ATIONALITY_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (33, '缅甸', '缅甸', '33', null, null, null, null, null, null, null, null);

insert into nationality_dict (NATIONALITY_CD, NATIONALITY_NAME, NATIONALITY_DESCRIPTION, ATIONALITY_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (34, '博茨瓦纳', '博茨瓦纳', '34', null, null, null, null, null, null, null, null);

insert into nationality_dict (NATIONALITY_CD, NATIONALITY_NAME, NATIONALITY_DESCRIPTION, ATIONALITY_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (35, '白俄罗斯', '白俄罗斯', '35', null, null, null, null, null, null, null, null);

insert into nationality_dict (NATIONALITY_CD, NATIONALITY_NAME, NATIONALITY_DESCRIPTION, ATIONALITY_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (36, '中非共和国', '中非共和国', '36', null, null, null, null, null, null, null, null);

insert into nationality_dict (NATIONALITY_CD, NATIONALITY_NAME, NATIONALITY_DESCRIPTION, ATIONALITY_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (37, '加拿大', '加拿大', '37', null, null, null, null, null, null, null, null);

insert into nationality_dict (NATIONALITY_CD, NATIONALITY_NAME, NATIONALITY_DESCRIPTION, ATIONALITY_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (38, '瑞士', '瑞士', '38', null, null, null, null, null, null, null, null);

insert into nationality_dict (NATIONALITY_CD, NATIONALITY_NAME, NATIONALITY_DESCRIPTION, ATIONALITY_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (39, '智利', '智利', '39', null, null, null, null, null, null, null, null);

insert into nationality_dict (NATIONALITY_CD, NATIONALITY_NAME, NATIONALITY_DESCRIPTION, ATIONALITY_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (40, '象牙海岸', '象牙海岸', '40', null, null, null, null, null, null, null, null);

insert into nationality_dict (NATIONALITY_CD, NATIONALITY_NAME, NATIONALITY_DESCRIPTION, ATIONALITY_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (41, '喀麦隆', '喀麦隆', '41', null, null, null, null, null, null, null, null);

insert into nationality_dict (NATIONALITY_CD, NATIONALITY_NAME, NATIONALITY_DESCRIPTION, ATIONALITY_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (42, '刚果', '刚果', '42', null, null, null, null, null, null, null, null);

insert into nationality_dict (NATIONALITY_CD, NATIONALITY_NAME, NATIONALITY_DESCRIPTION, ATIONALITY_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (43, '哥伦比亚', '哥伦比亚', '43', null, null, null, null, null, null, null, null);

insert into nationality_dict (NATIONALITY_CD, NATIONALITY_NAME, NATIONALITY_DESCRIPTION, ATIONALITY_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (44, '科摩罗', '科摩罗', '44', null, null, null, null, null, null, null, null);

insert into nationality_dict (NATIONALITY_CD, NATIONALITY_NAME, NATIONALITY_DESCRIPTION, ATIONALITY_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (45, '佛得角（维得角）', '佛得角（维得角）', '45', null, null, null, null, null, null, null, null);

insert into nationality_dict (NATIONALITY_CD, NATIONALITY_NAME, NATIONALITY_DESCRIPTION, ATIONALITY_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (46, '哥斯达黎加', '哥斯达黎加', '46', null, null, null, null, null, null, null, null);

insert into nationality_dict (NATIONALITY_CD, NATIONALITY_NAME, NATIONALITY_DESCRIPTION, ATIONALITY_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (47, '古巴', '古巴', '47', null, null, null, null, null, null, null, null);

insert into nationality_dict (NATIONALITY_CD, NATIONALITY_NAME, NATIONALITY_DESCRIPTION, ATIONALITY_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (48, '开曼群岛', '开曼群岛', '48', null, null, null, null, null, null, null, null);

insert into nationality_dict (NATIONALITY_CD, NATIONALITY_NAME, NATIONALITY_DESCRIPTION, ATIONALITY_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (49, '塞浦路斯', '塞浦路斯', '49', null, null, null, null, null, null, null, null);

insert into nationality_dict (NATIONALITY_CD, NATIONALITY_NAME, NATIONALITY_DESCRIPTION, ATIONALITY_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (50, '德国', '德国', '50', null, null, null, null, null, null, null, null);

insert into nationality_dict (NATIONALITY_CD, NATIONALITY_NAME, NATIONALITY_DESCRIPTION, ATIONALITY_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (51, '吉布提', '吉布提', '51', null, null, null, null, null, null, null, null);

insert into nationality_dict (NATIONALITY_CD, NATIONALITY_NAME, NATIONALITY_DESCRIPTION, ATIONALITY_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (52, '多米尼加', '多米尼加', '52', null, null, null, null, null, null, null, null);

insert into nationality_dict (NATIONALITY_CD, NATIONALITY_NAME, NATIONALITY_DESCRIPTION, ATIONALITY_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (53, '丹麦', '丹麦', '53', null, null, null, null, null, null, null, null);

insert into nationality_dict (NATIONALITY_CD, NATIONALITY_NAME, NATIONALITY_DESCRIPTION, ATIONALITY_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (54, '阿尼及利亚', '阿尼及利亚', '54', null, null, null, null, null, null, null, null);

insert into nationality_dict (NATIONALITY_CD, NATIONALITY_NAME, NATIONALITY_DESCRIPTION, ATIONALITY_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (55, '厄瓜多尔', '厄瓜多尔', '55', null, null, null, null, null, null, null, null);

insert into nationality_dict (NATIONALITY_CD, NATIONALITY_NAME, NATIONALITY_DESCRIPTION, ATIONALITY_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (56, '埃及', '埃及', '56', null, null, null, null, null, null, null, null);

insert into nationality_dict (NATIONALITY_CD, NATIONALITY_NAME, NATIONALITY_DESCRIPTION, ATIONALITY_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (57, '西萨哈拉', '西萨哈拉', '57', null, null, null, null, null, null, null, null);

insert into nationality_dict (NATIONALITY_CD, NATIONALITY_NAME, NATIONALITY_DESCRIPTION, ATIONALITY_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (58, '西班牙', '西班牙', '58', null, null, null, null, null, null, null, null);

insert into nationality_dict (NATIONALITY_CD, NATIONALITY_NAME, NATIONALITY_DESCRIPTION, ATIONALITY_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (59, '埃塞俄比亚', '埃塞俄比亚', '59', null, null, null, null, null, null, null, null);

insert into nationality_dict (NATIONALITY_CD, NATIONALITY_NAME, NATIONALITY_DESCRIPTION, ATIONALITY_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (60, '芬兰', '芬兰', '60', null, null, null, null, null, null, null, null);

insert into nationality_dict (NATIONALITY_CD, NATIONALITY_NAME, NATIONALITY_DESCRIPTION, ATIONALITY_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (61, '斐济', '斐济', '61', null, null, null, null, null, null, null, null);

insert into nationality_dict (NATIONALITY_CD, NATIONALITY_NAME, NATIONALITY_DESCRIPTION, ATIONALITY_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (62, '加逢', '加逢', '62', null, null, null, null, null, null, null, null);

insert into nationality_dict (NATIONALITY_CD, NATIONALITY_NAME, NATIONALITY_DESCRIPTION, ATIONALITY_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (63, '加纳', '加纳', '63', null, null, null, null, null, null, null, null);

insert into nationality_dict (NATIONALITY_CD, NATIONALITY_NAME, NATIONALITY_DESCRIPTION, ATIONALITY_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (64, '几内亚', '几内亚', '64', null, null, null, null, null, null, null, null);

insert into nationality_dict (NATIONALITY_CD, NATIONALITY_NAME, NATIONALITY_DESCRIPTION, ATIONALITY_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (65, '冈比亚', '冈比亚', '65', null, null, null, null, null, null, null, null);

insert into nationality_dict (NATIONALITY_CD, NATIONALITY_NAME, NATIONALITY_DESCRIPTION, ATIONALITY_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (66, '几内亚比索', '几内亚比索', '66', null, null, null, null, null, null, null, null);

insert into nationality_dict (NATIONALITY_CD, NATIONALITY_NAME, NATIONALITY_DESCRIPTION, ATIONALITY_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (67, '赤道几内亚', '赤道几内亚', '67', null, null, null, null, null, null, null, null);

insert into nationality_dict (NATIONALITY_CD, NATIONALITY_NAME, NATIONALITY_DESCRIPTION, ATIONALITY_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (68, '希腊', '希腊', '68', null, null, null, null, null, null, null, null);

insert into nationality_dict (NATIONALITY_CD, NATIONALITY_NAME, NATIONALITY_DESCRIPTION, ATIONALITY_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (69, '格林纳达', '格林纳达', '69', null, null, null, null, null, null, null, null);

insert into nationality_dict (NATIONALITY_CD, NATIONALITY_NAME, NATIONALITY_DESCRIPTION, ATIONALITY_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (70, '格林兰', '格林兰', '70', null, null, null, null, null, null, null, null);

insert into nationality_dict (NATIONALITY_CD, NATIONALITY_NAME, NATIONALITY_DESCRIPTION, ATIONALITY_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (71, '危地马拉', '危地马拉', '71', null, null, null, null, null, null, null, null);

insert into nationality_dict (NATIONALITY_CD, NATIONALITY_NAME, NATIONALITY_DESCRIPTION, ATIONALITY_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (72, '关岛', '关岛', '72', null, null, null, null, null, null, null, null);

insert into nationality_dict (NATIONALITY_CD, NATIONALITY_NAME, NATIONALITY_DESCRIPTION, ATIONALITY_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (73, '圭亚那', '圭亚那', '73', null, null, null, null, null, null, null, null);

insert into nationality_dict (NATIONALITY_CD, NATIONALITY_NAME, NATIONALITY_DESCRIPTION, ATIONALITY_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (74, '洪都拉斯', '洪都拉斯', '74', null, null, null, null, null, null, null, null);

insert into nationality_dict (NATIONALITY_CD, NATIONALITY_NAME, NATIONALITY_DESCRIPTION, ATIONALITY_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (75, '海地', '海地', '75', null, null, null, null, null, null, null, null);

insert into nationality_dict (NATIONALITY_CD, NATIONALITY_NAME, NATIONALITY_DESCRIPTION, ATIONALITY_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (76, '匈牙利', '匈牙利', '76', null, null, null, null, null, null, null, null);

insert into nationality_dict (NATIONALITY_CD, NATIONALITY_NAME, NATIONALITY_DESCRIPTION, ATIONALITY_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (77, '上沃尼特', '上沃尼特', '77', null, null, null, null, null, null, null, null);

insert into nationality_dict (NATIONALITY_CD, NATIONALITY_NAME, NATIONALITY_DESCRIPTION, ATIONALITY_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (78, '印尼', '印尼', '78', null, null, null, null, null, null, null, null);

insert into nationality_dict (NATIONALITY_CD, NATIONALITY_NAME, NATIONALITY_DESCRIPTION, ATIONALITY_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (79, '印度', '印度', '79', null, null, null, null, null, null, null, null);

insert into nationality_dict (NATIONALITY_CD, NATIONALITY_NAME, NATIONALITY_DESCRIPTION, ATIONALITY_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (80, '爱尔兰', '爱尔兰', '80', null, null, null, null, null, null, null, null);

insert into nationality_dict (NATIONALITY_CD, NATIONALITY_NAME, NATIONALITY_DESCRIPTION, ATIONALITY_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (81, '伊朗', '伊朗', '81', null, null, null, null, null, null, null, null);

insert into nationality_dict (NATIONALITY_CD, NATIONALITY_NAME, NATIONALITY_DESCRIPTION, ATIONALITY_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (82, '伊拉克', '伊拉克', '82', null, null, null, null, null, null, null, null);

insert into nationality_dict (NATIONALITY_CD, NATIONALITY_NAME, NATIONALITY_DESCRIPTION, ATIONALITY_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (83, '冰岛', '冰岛', '83', null, null, null, null, null, null, null, null);

insert into nationality_dict (NATIONALITY_CD, NATIONALITY_NAME, NATIONALITY_DESCRIPTION, ATIONALITY_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (84, '以色利', '以色利', '84', null, null, null, null, null, null, null, null);

insert into nationality_dict (NATIONALITY_CD, NATIONALITY_NAME, NATIONALITY_DESCRIPTION, ATIONALITY_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (85, '意大利', '意大利', '85', null, null, null, null, null, null, null, null);

insert into nationality_dict (NATIONALITY_CD, NATIONALITY_NAME, NATIONALITY_DESCRIPTION, ATIONALITY_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (86, '牙买加', '牙买加', '86', null, null, null, null, null, null, null, null);

insert into nationality_dict (NATIONALITY_CD, NATIONALITY_NAME, NATIONALITY_DESCRIPTION, ATIONALITY_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (87, '约旦', '约旦', '87', null, null, null, null, null, null, null, null);

insert into nationality_dict (NATIONALITY_CD, NATIONALITY_NAME, NATIONALITY_DESCRIPTION, ATIONALITY_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (88, '日本', '日本', '88', null, null, null, null, null, null, null, null);

insert into nationality_dict (NATIONALITY_CD, NATIONALITY_NAME, NATIONALITY_DESCRIPTION, ATIONALITY_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (89, '肯尼亚', '肯尼亚', '89', null, null, null, null, null, null, null, null);

insert into nationality_dict (NATIONALITY_CD, NATIONALITY_NAME, NATIONALITY_DESCRIPTION, ATIONALITY_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (90, '柬埔寨', '柬埔寨', '90', null, null, null, null, null, null, null, null);

insert into nationality_dict (NATIONALITY_CD, NATIONALITY_NAME, NATIONALITY_DESCRIPTION, ATIONALITY_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (91, '韩国', '韩国', '91', null, null, null, null, null, null, null, null);

insert into nationality_dict (NATIONALITY_CD, NATIONALITY_NAME, NATIONALITY_DESCRIPTION, ATIONALITY_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (92, '科威特', '科威特', '92', null, null, null, null, null, null, null, null);

insert into nationality_dict (NATIONALITY_CD, NATIONALITY_NAME, NATIONALITY_DESCRIPTION, ATIONALITY_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (93, '老挝', '老挝', '93', null, null, null, null, null, null, null, null);

insert into nationality_dict (NATIONALITY_CD, NATIONALITY_NAME, NATIONALITY_DESCRIPTION, ATIONALITY_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (94, '黎巴嫩', '黎巴嫩', '94', null, null, null, null, null, null, null, null);

insert into nationality_dict (NATIONALITY_CD, NATIONALITY_NAME, NATIONALITY_DESCRIPTION, ATIONALITY_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (95, '利比里亚', '利比里亚', '95', null, null, null, null, null, null, null, null);

insert into nationality_dict (NATIONALITY_CD, NATIONALITY_NAME, NATIONALITY_DESCRIPTION, ATIONALITY_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (96, '斯里兰卡', '斯里兰卡', '96', null, null, null, null, null, null, null, null);

insert into nationality_dict (NATIONALITY_CD, NATIONALITY_NAME, NATIONALITY_DESCRIPTION, ATIONALITY_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (97, '莱索托', '莱索托', '97', null, null, null, null, null, null, null, null);

insert into nationality_dict (NATIONALITY_CD, NATIONALITY_NAME, NATIONALITY_DESCRIPTION, ATIONALITY_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (98, '卢森堡', '卢森堡', '98', null, null, null, null, null, null, null, null);

insert into nationality_dict (NATIONALITY_CD, NATIONALITY_NAME, NATIONALITY_DESCRIPTION, ATIONALITY_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (99, '澳门', '澳门', '99', null, null, null, null, null, null, null, null);

insert into nationality_dict (NATIONALITY_CD, NATIONALITY_NAME, NATIONALITY_DESCRIPTION, ATIONALITY_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (100, '摩洛哥', '摩洛哥', '100', null, null, null, null, null, null, null, null);

insert into nationality_dict (NATIONALITY_CD, NATIONALITY_NAME, NATIONALITY_DESCRIPTION, ATIONALITY_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (101, '摩纳哥', '摩纳哥', '101', null, null, null, null, null, null, null, null);

insert into nationality_dict (NATIONALITY_CD, NATIONALITY_NAME, NATIONALITY_DESCRIPTION, ATIONALITY_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (102, '马达加斯加', '马达加斯加', '102', null, null, null, null, null, null, null, null);

insert into nationality_dict (NATIONALITY_CD, NATIONALITY_NAME, NATIONALITY_DESCRIPTION, ATIONALITY_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (103, '马尔代夫', '马尔代夫', '103', null, null, null, null, null, null, null, null);

insert into nationality_dict (NATIONALITY_CD, NATIONALITY_NAME, NATIONALITY_DESCRIPTION, ATIONALITY_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (104, '墨西哥', '墨西哥', '104', null, null, null, null, null, null, null, null);

insert into nationality_dict (NATIONALITY_CD, NATIONALITY_NAME, NATIONALITY_DESCRIPTION, ATIONALITY_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (105, '中途岛', '中途岛', '105', null, null, null, null, null, null, null, null);

insert into nationality_dict (NATIONALITY_CD, NATIONALITY_NAME, NATIONALITY_DESCRIPTION, ATIONALITY_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (106, '马里', '马里', '106', null, null, null, null, null, null, null, null);

insert into nationality_dict (NATIONALITY_CD, NATIONALITY_NAME, NATIONALITY_DESCRIPTION, ATIONALITY_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (107, '马耳他', '马耳他', '107', null, null, null, null, null, null, null, null);

insert into nationality_dict (NATIONALITY_CD, NATIONALITY_NAME, NATIONALITY_DESCRIPTION, ATIONALITY_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (108, '蒙古', '蒙古', '108', null, null, null, null, null, null, null, null);

insert into nationality_dict (NATIONALITY_CD, NATIONALITY_NAME, NATIONALITY_DESCRIPTION, ATIONALITY_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (109, '英桑比克', '英桑比克', '109', null, null, null, null, null, null, null, null);

insert into nationality_dict (NATIONALITY_CD, NATIONALITY_NAME, NATIONALITY_DESCRIPTION, ATIONALITY_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (301, '其他1', '其他1', '400', null, null, null, null, null, null, null, null);

insert into degree_type_dict (DEGREE_TYPE_CD, DEGREE_TYPE_CODE, DEGREE_TYPE_NAME, INPUT_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (1, '0', '未确定', '0', null, null, null, null, null, null, null, null);

insert into degree_type_dict (DEGREE_TYPE_CD, DEGREE_TYPE_CODE, DEGREE_TYPE_NAME, INPUT_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (2, '1', '研究生硕士', '1', null, null, null, null, null, null, null, null);

insert into degree_type_dict (DEGREE_TYPE_CD, DEGREE_TYPE_CODE, DEGREE_TYPE_NAME, INPUT_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (3, '2', '大学本科', '2', null, null, null, null, null, null, null, null);

insert into degree_type_dict (DEGREE_TYPE_CD, DEGREE_TYPE_CODE, DEGREE_TYPE_NAME, INPUT_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (4, '3', '大专', '3', null, null, null, null, null, null, null, null);

insert into degree_type_dict (DEGREE_TYPE_CD, DEGREE_TYPE_CODE, DEGREE_TYPE_NAME, INPUT_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (5, '4', '中专', '4', null, null, null, null, null, null, null, null);

insert into degree_type_dict (DEGREE_TYPE_CD, DEGREE_TYPE_CODE, DEGREE_TYPE_NAME, INPUT_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (6, '5', '技工学校', '5', null, null, null, null, null, null, null, null);

insert into degree_type_dict (DEGREE_TYPE_CD, DEGREE_TYPE_CODE, DEGREE_TYPE_NAME, INPUT_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (7, '6', '高中', '6', null, null, null, null, null, null, null, null);

insert into degree_type_dict (DEGREE_TYPE_CD, DEGREE_TYPE_CODE, DEGREE_TYPE_NAME, INPUT_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (8, '7', '初中', '7', null, null, null, null, null, null, null, null);

insert into degree_type_dict (DEGREE_TYPE_CD, DEGREE_TYPE_CODE, DEGREE_TYPE_NAME, INPUT_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (9, '8', '小学', '8', null, null, null, null, null, null, null, null);

insert into degree_type_dict (DEGREE_TYPE_CD, DEGREE_TYPE_CODE, DEGREE_TYPE_NAME, INPUT_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (10, '9', '文盲及半文盲', '9', null, null, null, null, null, null, null, null);

insert into degree_type_dict (DEGREE_TYPE_CD, DEGREE_TYPE_CODE, DEGREE_TYPE_NAME, INPUT_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (11, '10', '博士', '10', null, null, null, null, null, null, null, null);



insert into marital_status_dict (MARITAL_STATUS_CD, MARITAL_STATUS_CODE, MARITAL_STATUS_NAME, INPUT_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (1, 'A', '分居', 'FJ', 'pm', null, '分居', null, null, null, null, null);

insert into marital_status_dict (MARITAL_STATUS_CD, MARITAL_STATUS_CODE, MARITAL_STATUS_NAME, INPUT_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (2, 'D', '失婚', 'SH', 'pm', null, '失婚', null, null, null, null, null);

insert into marital_status_dict (MARITAL_STATUS_CD, MARITAL_STATUS_CODE, MARITAL_STATUS_NAME, INPUT_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (3, 'M', '已婚', 'YH', 'pm', null, '已婚', null, null, null, null, null);

insert into marital_status_dict (MARITAL_STATUS_CD, MARITAL_STATUS_CODE, MARITAL_STATUS_NAME, INPUT_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (4, 'S', '未婚', 'WH', 'pm', null, '未婚', null, null, null, null, null);

insert into marital_status_dict (MARITAL_STATUS_CD, MARITAL_STATUS_CODE, MARITAL_STATUS_NAME, INPUT_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (5, 'W', '丧偶', 'SO', 'pm', null, '丧偶', null, null, null, null, null);

insert into marital_status_dict (MARITAL_STATUS_CD, MARITAL_STATUS_CODE, MARITAL_STATUS_NAME, INPUT_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (6, 'R', '再婚', 'ZH', 'pm', null, '再婚', null, null, null, null, null);

insert into marital_status_dict (MARITAL_STATUS_CD, MARITAL_STATUS_CODE, MARITAL_STATUS_NAME, INPUT_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (7, 'U', '未知', 'UU', 'pm', null, '未知', null, null, null, null, null);

insert into marital_status_dict (MARITAL_STATUS_CD, MARITAL_STATUS_CODE, MARITAL_STATUS_NAME, INPUT_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (8, 'O', '其他', 'OO', 'pm', null, '其他', null, null, null, null, null);



insert into Gender_Dict (GENDER_CD, GENDER_NAME, GENDER_DESCRIPTION, GENDER_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (1, 'Female', 'Female', 'F', 'pm', null, '女性', null, null, null, null, null);

insert into Gender_Dict (GENDER_CD, GENDER_NAME, GENDER_DESCRIPTION, GENDER_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (2, 'Male', 'Male', 'M', 'pm', null, '男性', null, null, null, null, null);

insert into Gender_Dict (GENDER_CD, GENDER_NAME, GENDER_DESCRIPTION, GENDER_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (3, 'Other', 'Other', 'O', 'pm', null, '其他', null, null, null, null, null);

insert into Gender_Dict (GENDER_CD, GENDER_NAME, GENDER_DESCRIPTION, GENDER_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (4, 'Unknown', 'Unknown', 'U', 'pm', null, '不知道', null, null, null, null, null);

insert into Gender_Dict (GENDER_CD, GENDER_NAME, GENDER_DESCRIPTION, GENDER_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (5, 'Ambiguous', 'Ambiguous', 'A', 'pm', null, '不明确的', null, null, null, null, null);

insert into Gender_Dict (GENDER_CD, GENDER_NAME, GENDER_DESCRIPTION, GENDER_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (6, 'NotApplicable', 'NotApplicable', 'N', 'pm', null, '不适用', null, null, null, null, null);


insert into audit_event_type_dict (AUDIT_EVENT_TYPE_CD, AUDIT_EVENT_TYPE_NAME, AUDIT_EVENT_TYPE_DESCRIPTION, AUDIT_EVENT_TYPE_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (800001, '新增病人注册，无就诊信息', null, '800001', null, null, null, null, null, null, null, null);

insert into audit_event_type_dict (AUDIT_EVENT_TYPE_CD, AUDIT_EVENT_TYPE_NAME, AUDIT_EVENT_TYPE_DESCRIPTION, AUDIT_EVENT_TYPE_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (800002, '新增病人注册', null, '800002', null, null, null, null, null, null, null, null);

insert into audit_event_type_dict (AUDIT_EVENT_TYPE_CD, AUDIT_EVENT_TYPE_NAME, AUDIT_EVENT_TYPE_DESCRIPTION, AUDIT_EVENT_TYPE_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (800003, '历史病人注册，只记录就诊信息', null, '800003', null, null, null, null, null, null, null, null);

insert into audit_event_type_dict (AUDIT_EVENT_TYPE_CD, AUDIT_EVENT_TYPE_NAME, AUDIT_EVENT_TYPE_DESCRIPTION, AUDIT_EVENT_TYPE_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (800004, '病人更新，不更新就诊信息', null, '800004', null, null, null, null, null, null, null, null);

insert into audit_event_type_dict (AUDIT_EVENT_TYPE_CD, AUDIT_EVENT_TYPE_NAME, AUDIT_EVENT_TYPE_DESCRIPTION, AUDIT_EVENT_TYPE_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (800005, '病人更新', null, '800005', null, null, null, null, null, null, null, null);

insert into audit_event_type_dict (AUDIT_EVENT_TYPE_CD, AUDIT_EVENT_TYPE_NAME, AUDIT_EVENT_TYPE_DESCRIPTION, AUDIT_EVENT_TYPE_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (800006, '病人合并', null, '800006', null, null, null, null, null, null, null, null);

insert into audit_event_type_dict (AUDIT_EVENT_TYPE_CD, AUDIT_EVENT_TYPE_NAME, AUDIT_EVENT_TYPE_DESCRIPTION, AUDIT_EVENT_TYPE_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (800007, '病人就诊状态更新', null, '800007', null, null, null, null, null, null, null, null);

insert into audit_event_type_dict (AUDIT_EVENT_TYPE_CD, AUDIT_EVENT_TYPE_NAME, AUDIT_EVENT_TYPE_DESCRIPTION, AUDIT_EVENT_TYPE_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (800008, '取消病人入院，删除入院记录', null, '800008', null, null, null, null, null, null, null, null);

insert into audit_event_type_dict (AUDIT_EVENT_TYPE_CD, AUDIT_EVENT_TYPE_NAME, AUDIT_EVENT_TYPE_DESCRIPTION, AUDIT_EVENT_TYPE_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (800009, '删除病人，及所有相关就诊信息', null, '800009', null, null, null, null, null, null, null, null);

insert into audit_event_type_dict (AUDIT_EVENT_TYPE_CD, AUDIT_EVENT_TYPE_NAME, AUDIT_EVENT_TYPE_DESCRIPTION, AUDIT_EVENT_TYPE_CODE, CREATE_NAME, CREATE_DATE, COMMENTS, CUSTOM1, CUSTOM2, CUSTOM3, CUSTOM4, CUSTOM5)
values (800010, '修改病人ID', null, '800010', null, null, null, null, null, null, null, null);