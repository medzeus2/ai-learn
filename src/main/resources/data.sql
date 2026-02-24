INSERT INTO t_person(name, gender, age, id_card, status)
SELECT '张三', 'MALE', 30, '110101199001011234', 'ENABLED'
WHERE NOT EXISTS (SELECT 1 FROM t_person WHERE id_card='110101199001011234');

INSERT INTO t_person(name, gender, age, id_card, status)
SELECT '李四', 'FEMALE', 28, '110101199602021234', 'DISABLED'
WHERE NOT EXISTS (SELECT 1 FROM t_person WHERE id_card='110101199602021234');
