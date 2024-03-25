insert into blog.users (id, name, pass, enabled, created) values (
    'ebc18ab5-e6d4-4b85-b40c-e575615c3225',
    'petryuk',
    '$2a$10$8ONYhbp2yHqjGjty6.duqO.tsx4304Z.3pt0AdiPywmapfT.CaIUG',
    true,
    '2024-03-24'
);

insert into blog.users (id, name, pass, enabled, created) values (
    '0f6d0113-3365-442b-9d78-3a1fea559e2d',
    'zayanimal',
    '$2a$10$8ONYhbp2yHqjGjty6.duqO.tsx4304Z.3pt0AdiPywmapfT.CaIUG',
    true,
    '2024-03-25'
);

insert into blog.topic (id, name) values
(1, 'Путешествия'),
(2, 'Музыка'),
(3, 'Спорт'),
(4, 'Разработка'),
(5, 'Мотоциклы');

insert into blog.user_topic (user_id, topic_id) values
('ebc18ab5-e6d4-4b85-b40c-e575615c3225', 3),
('ebc18ab5-e6d4-4b85-b40c-e575615c3225', 4),
('ebc18ab5-e6d4-4b85-b40c-e575615c3225', 5),
('0f6d0113-3365-442b-9d78-3a1fea559e2d', 1),
('0f6d0113-3365-442b-9d78-3a1fea559e2d', 2),
('0f6d0113-3365-442b-9d78-3a1fea559e2d', 3);

insert into blog.post (id, created, post, user_id, is_public) values (
    (values next value for blog.post_seq),
    '2024-03-10 15:22:52.370727',
    '{
      "time": 1710073372105,
      "version": "2.29.0",
      "blocks": [
        {
          "id": "TqbSfx3p0p",
          "type": "header",
          "data": {
            "text": "Спорт это прекрасно"
          }
        },
        {
          "id": "fNx6IY27nO",
          "type": "paragraph",
          "data": {
            "text": "Меня беспокоят Олимпийские игры в Париже. Они станут первоклассной мишенью для террористов, — заявил Фиттон‑Браун New York Times."
          }
        }
      ]
    }',
    'ebc18ab5-e6d4-4b85-b40c-e575615c3225',
    true
);

insert into blog.post (id, created, post, user_id, is_public) values (
    (values next value for blog.post_seq),
    '2024-03-20 15:22:52.370727',
    '{
    "time": 1710073372105,
    "version": "2.29.0",
    "blocks": [
     {
       "id": "TqbSfx3p0p",
       "type": "header",
       "data": {
         "text": "Я люблю слушать джаз"
       }
     },
     {
       "id": "fNx6IY27nO",
       "type": "paragraph",
       "data": {
         "text": "Джаз как мультикультурное явление обогащался различными региональными музыкальными особенностями[6]. На развитие джаза также повлияли духовые оркестры, марширующие по праздникам на улицах городов США, а также военные и танцевальные оркестры."
       }
     }
    ]
    }',
    '0f6d0113-3365-442b-9d78-3a1fea559e2d',
    true
);

insert into blog.post (id, created, post, user_id, is_public) values (
    (values next value for blog.post_seq),
    '2024-03-22 15:22:52.370727',
    '{
    "time": 1710073372105,
    "version": "2.29.0",
    "blocks": [
     {
       "id": "TqbSfx3p0p",
       "type": "header",
       "data": {
         "text": "Путешествие на килиманджаро"
       }
     },
     {
       "id": "fNx6IY27nO",
       "type": "paragraph",
       "data": {
         "text": "Килиманджа́ро — высочайший стратовулкан Африки, находящийся на северо-востоке Танзании, высочайшая точка континента. C 1902 по 1918 годы назывался Вершина Кайзера Вильгельма. Килиманджаро возвышается над плоскогорьем Масаи, которое расположено на высоте 900 метров над уровнем моря."
       }
     }
    ]
    }',
    '0f6d0113-3365-442b-9d78-3a1fea559e2d',
    true
);

insert into blog.post_topic (post_id, topic_id) values
(1, 3),
(2, 2),
(3, 1);
