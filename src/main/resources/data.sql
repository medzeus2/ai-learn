INSERT INTO t_person(name, name_pinyin, name_initials, gender, age, id_card, status)
SELECT '张三', 'zhangsan', 'zs', 'MALE', 30, '110101199001011234', 'ENABLED'
WHERE NOT EXISTS (SELECT 1 FROM t_person WHERE id_card='110101199001011234');

INSERT INTO t_person(name, name_pinyin, name_initials, gender, age, id_card, status)
SELECT '李四', 'lisi', 'ls', 'FEMALE', 28, '110101199602021234', 'DISABLED'
WHERE NOT EXISTS (SELECT 1 FROM t_person WHERE id_card='110101199602021234');

UPDATE t_person
SET name_pinyin = CASE
        WHEN name = '张三' THEN 'zhangsan'
        WHEN name = '李四' THEN 'lisi'
        ELSE name_pinyin
    END,
    name_initials = CASE
        WHEN name = '张三' THEN 'zs'
        WHEN name = '李四' THEN 'ls'
        ELSE name_initials
    END
WHERE name_pinyin = '' OR name_initials = '';
