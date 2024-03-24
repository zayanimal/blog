insert into blog.users (id, name, pass, enabled, created) values (
    'ebc18ab5-e6d4-4b85-b40c-e575615c3225',
    'petryuk',
    '$2a$10$8ONYhbp2yHqjGjty6.duqO.tsx4304Z.3pt0AdiPywmapfT.CaIUG',
    true,
    '2024-03-24'
);

insert into blog.topic (id, name) values
(1, 'Путешествия'),
(2, 'Музыка'),
(3, 'Спорт'),
(4, 'Разработка'),
(5, 'Мотоциклы');

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
            "text": "Это мой чудесный пааааспорт"
          }
        },
        {
          "id": "fNx6IY27nO",
          "type": "paragraph",
          "data": {
            "text": "Паспорт выполняется в форме брошюры, в которой делаются отметки о пересечении границы, а также проставляются (если не заключено соглашение о&nbsp;<a href=\"https://ru.wikipedia.org/wiki/%D0%91%D0%B5%D0%B7%D0%B2%D0%B8%D0%B7%D0%BE%D0%B2%D1%8B%D0%B9_%D1%80%D0%B5%D0%B6%D0%B8%D0%BC\">безвизовом режиме</a>)&nbsp;<a href=\"https://ru.wikipedia.org/wiki/%D0%92%D0%B8%D0%B7%D0%B0\">визы</a>, дающие право въезда в другую страну. Также он содержит идентификационную информацию: фотографию владельца, его&nbsp;<a href=\"https://ru.wikipedia.org/wiki/%D0%9F%D0%BE%D0%BB%D0%BD%D0%BE%D0%B5_%D0%B8%D0%BC%D1%8F\">имя</a>, дату рождения,&nbsp;<a href=\"https://ru.wikipedia.org/wiki/%D0%9F%D0%B0%D1%81%D0%BF%D0%BE%D1%80%D1%82%D0%BD%D1%8B%D0%B9_%D0%BF%D0%BE%D0%BB\">пол</a>, место рождения и гражданство (название страны, выдавшей паспорт). Паспорт предназначен для использования по всему миру, и поэтому паспорта всех стран до некоторой степени унифицированы, а надписи на&nbsp;<a href=\"https://ru.wikipedia.org/wiki/%D0%AF%D0%B7%D1%8B%D0%BA%D0%B8_%D0%BC%D0%B8%D1%80%D0%B0\">национальных языках</a>, как правило, дублируются на&nbsp;<a href=\"https://ru.wikipedia.org/wiki/%D0%90%D0%BD%D0%B3%D0%BB%D0%B8%D0%B9%D1%81%D0%BA%D0%B8%D0%B9_%D1%8F%D0%B7%D1%8B%D0%BA\">английском</a>&nbsp;и/или&nbsp;<a href=\"https://ru.wikipedia.org/wiki/%D0%A4%D1%80%D0%B0%D0%BD%D1%86%D1%83%D0%B7%D1%81%D0%BA%D0%B8%D0%B9_%D1%8F%D0%B7%D1%8B%D0%BA\">французском</a>&nbsp;языке. В XXI веке многие страны мира переходят на&nbsp;<a href=\"https://ru.wikipedia.org/wiki/%D0%91%D0%B8%D0%BE%D0%BC%D0%B5%D1%82%D1%80%D0%B8%D1%87%D0%B5%D1%81%D0%BA%D0%B8%D0%B9_%D0%BF%D0%B0%D1%81%D0%BF%D0%BE%D1%80%D1%82\">паспорта, содержащие биометрическую информацию</a>."
          }
        },
        {
          "id": "8ZhBrrp_8c",
          "type": "image",
          "data": {
            "caption": "Я так доволен",
            "file": {
              "url": "https://downloader.disk.yandex.ru/preview/bf848b05b75c4257da0f4a1fdcd09df3c1b00790aa18a6c76c04dcb475d67501/inf/KbglbWOTKizt0qQxTb7oKiCHSUYWO4EFpjbxQuqcp2BxZUHMBraHP5JxmcgBQK_nYd6M9LhTuoKlH55MKHzVfQ%3D%3D?uid=305198944&filename=82121f43036140e3a4ac2f90a2ab4031.jpeg&disposition=inline&hash=&limit=0&content_type=image%2Fjpeg&owner_uid=305198944&tknv=v2&size=XL&crop=0"
            },
            "stretched": false,
            "withBackground": false,
            "withBorder": false
          }
        },
        {
          "id": "7aCaRV63On",
          "type": "paragraph",
          "data": {
            "text": "Да это реально очень интересно"
          }
        }
      ]
    }',
    'ebc18ab5-e6d4-4b85-b40c-e575615c3225',
    true
);

insert into blog.user_topic (user_id, topic_id) values
('ebc18ab5-e6d4-4b85-b40c-e575615c3225', 3),
('ebc18ab5-e6d4-4b85-b40c-e575615c3225', 4),
('ebc18ab5-e6d4-4b85-b40c-e575615c3225', 5);

insert into blog.post_topic (post_id, topic_id) values
(1, 1),
(1, 2),
(1, 3);
