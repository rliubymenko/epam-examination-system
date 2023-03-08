-- TRUNCATE TABLE answer RESTART IDENTITY CASCADE;
-- TRUNCATE TABLE question RESTART IDENTITY CASCADE;
-- TRUNCATE TABLE epam_user_test RESTART IDENTITY CASCADE;
-- TRUNCATE TABLE test RESTART IDENTITY CASCADE;
-- TRUNCATE TABLE subject RESTART IDENTITY CASCADE;
-- TRUNCATE TABLE epam_user RESTART IDENTITY CASCADE;
-- TRUNCATE TABLE role RESTART IDENTITY CASCADE;

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

-- Population the subject table
INSERT INTO public.subject (id, uuid, name, description, created) OVERRIDING SYSTEM VALUE
VALUES (1, '5a0d1959-0151-478b-bd01-0629e2be70e8', 'IPv6',
        'Pellentesque at nulla. Suspendisse potenti. Cras in purus eu magna vulputate luctus.', '2023-01-19 14:32:23');
INSERT INTO public.subject (id, uuid, name, description, created) OVERRIDING SYSTEM VALUE
VALUES (3, '195e19bd-4177-45b0-8dbe-77b98da47ac6', 'Psychopharmacology',
        'Cras non velit nec nisi vulputate nonummy. Maecenas tincidunt lacus at velit. Vivamus vel nulla eget eros elementum pellentesque.',
        '2023-01-19 14:32:23');
INSERT INTO public.subject (id, uuid, name, description, created) OVERRIDING SYSTEM VALUE
VALUES (4, '0951862c-813b-49a4-8887-c37def8d72f6', 'Blue Ocean Strategy',
        'Aliquam quis turpis eget elit sodales scelerisque. Mauris sit amet eros. Suspendisse accumsan tortor quis turpis.',
        '2023-01-19 14:32:23');
INSERT INTO public.subject (id, uuid, name, description, created) OVERRIDING SYSTEM VALUE
VALUES (5, 'b6026fb6-62a8-46fa-a921-b02c2f23877b', 'Sleep Apnea',
        'Maecenas tristique, est et tempus semper, est quam pharetra magna, ac consequat metus sapien ut nunc. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Mauris viverra diam vitae quam. Suspendisse potenti.',
        '2023-01-19 14:32:23');
INSERT INTO public.subject (id, uuid, name, description, created) OVERRIDING SYSTEM VALUE
VALUES (7, '0188c539-deec-4a12-b087-1f16b67ccc23', 'Credit Analysis',
        'Suspendisse potenti. In eleifend quam a odio. In hac habitasse platea dictumst.', '2023-01-19 14:32:23');
INSERT INTO public.subject (id, uuid, name, description, created) OVERRIDING SYSTEM VALUE
VALUES (8, '9c7e4d20-df7b-49ff-86a6-413c66037bc2', 'Jiu-Jitsu',
        'Phasellus sit amet erat. Nulla tempus. Vivamus in felis eu sapien cursus vestibulum.', '2023-01-19 14:32:23');
INSERT INTO public.subject (id, uuid, name, description, created) OVERRIDING SYSTEM VALUE
VALUES (9, '71ffcb88-8f71-40df-b632-75e81a629819', 'RNA',
        'Cras mi pede, malesuada in, imperdiet et, commodo vulputate, justo. In blandit ultrices enim. Lorem ipsum dolor sit amet, consectetuer adipiscing elit.',
        '2023-01-19 14:32:23');
INSERT INTO public.subject (id, uuid, name, description, created) OVERRIDING SYSTEM VALUE
VALUES (10, '6972df06-0771-4816-90eb-6d3bf7b33781', 'LDRA',
        'In quis justo. Maecenas rhoncus aliquam lacus. Morbi quis tortor id nulla ultrices aliquet.',
        '2023-01-19 14:32:23');
INSERT INTO public.subject (id, uuid, name, description, created) OVERRIDING SYSTEM VALUE
VALUES (11, 'b6d86cd6-b815-488a-bfe1-25523c8b73e1', 'NTSC',
        'Maecenas leo odio, condimentum id, luctus nec, molestie sed, justo. Pellentesque viverra pede ac diam. Cras pellentesque volutpat dui.',
        '2023-01-19 14:32:23');
INSERT INTO public.subject (id, uuid, name, description, created) OVERRIDING SYSTEM VALUE
VALUES (13, '10b4c772-29da-485e-979b-61f01acf4d67', 'Jitterbit',
        'Proin interdum mauris non ligula pellentesque ultrices. Phasellus id sapien in sapien iaculis congue. Vivamus metus arcu, adipiscing molestie, hendrerit at, vulputate vitae, nisl.',
        '2023-01-19 14:32:23');
INSERT INTO public.subject (id, uuid, name, description, created) OVERRIDING SYSTEM VALUE
VALUES (14, '39366172-c78c-4e2e-943b-af742b57a51e', 'Technical Documentation',
        'Morbi porttitor lorem id ligula. Suspendisse ornare consequat lectus. In est risus, auctor sed, tristique in, tempus sit amet, sem.',
        '2023-01-19 14:32:23');
INSERT INTO public.subject (id, uuid, name, description, created) OVERRIDING SYSTEM VALUE
VALUES (16, '836c29cc-baab-4b4d-86b5-7cd98f1c2052', 'MHRA',
        'Sed sagittis. Nam congue, risus semper porta volutpat, quam pede lobortis ligula, sit amet eleifend pede libero quis orci. Nullam molestie nibh in lectus.',
        '2023-01-19 14:32:23');
INSERT INTO public.subject (id, uuid, name, description, created) OVERRIDING SYSTEM VALUE
VALUES (17, 'a00fa20c-5ff0-4882-9467-59a4c99566e2', 'Norton Ghost',
        'Cras non velit nec nisi vulputate nonummy. Maecenas tincidunt lacus at velit. Vivamus vel nulla eget eros elementum pellentesque.',
        '2023-01-19 14:32:23');
INSERT INTO public.subject (id, uuid, name, description, created) OVERRIDING SYSTEM VALUE
VALUES (18, '5ca7d3d5-45fd-4ddf-bc57-384bf3afedcb', 'Fashion Buying',
        'Praesent blandit. Nam nulla. Integer pede justo, lacinia eget, tincidunt eget, tempus vel, pede.',
        '2023-01-19 14:32:23');
INSERT INTO public.subject (id, uuid, name, description, created) OVERRIDING SYSTEM VALUE
VALUES (19, '4a0b707f-1b00-4c7e-8618-d096dfba206f', 'MDaemon',
        'Maecenas tristique, est et tempus semper, est quam pharetra magna, ac consequat metus sapien ut nunc. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Mauris viverra diam vitae quam. Suspendisse potenti.',
        '2023-01-19 14:32:23');
INSERT INTO public.subject (id, uuid, name, description, created) OVERRIDING SYSTEM VALUE
VALUES (20, '44e4a286-a155-40c1-afd0-27ef6000f8bf', 'MXML',
        'Proin eu mi. Nulla ac enim. In tempor, turpis nec euismod scelerisque, quam turpis adipiscing lorem, vitae mattis nibh ligula nec sem.',
        '2023-01-19 14:32:23');
INSERT INTO public.subject (id, uuid, name, description, created) OVERRIDING SYSTEM VALUE
VALUES (22, '53159882-24ab-476f-8e0e-f3da79b11beb', 'fyghju', 'ytuj', '2023-01-19 16:50:05');
INSERT INTO public.subject (id, uuid, name, description, created) OVERRIDING SYSTEM VALUE
VALUES (23, '02cce77d-3695-4536-b840-cc33cb875899', 'ytui', 't65iy7', '2023-01-19 16:50:09');
INSERT INTO public.subject (id, uuid, name, description, created) OVERRIDING SYSTEM VALUE
VALUES (25, 'df08665d-c8c1-42be-9558-8ad0ff170bf4', 'Subject', 'decsc', '2023-01-27 21:02:01');
INSERT INTO public.subject (id, uuid, name, description, created) OVERRIDING SYSTEM VALUE
VALUES (30, '95bbc528-70f3-425e-a540-d7e5c1f650e9', 'NEW', '', '2023-02-23 22:25:32');

-- Population the test table
INSERT INTO public.test (id, uuid, name, description, complexity, duration, total_attempt_number, subject_id,
                         expiration_date, max_attempt_number, created) OVERRIDING SYSTEM VALUE
VALUES (126, '60415561-af95-475f-8bae-d7354aa969eb', 'Uraeginthus bengalus', 'In hac habitasse platea dictumst.',
        'easy', 900, 0, NULL, '2023-01-11 16:47:11', 1, '2023-01-19 14:32:46');
INSERT INTO public.test (id, uuid, name, description, complexity, duration, total_attempt_number, subject_id,
                         expiration_date, max_attempt_number, created) OVERRIDING SYSTEM VALUE
VALUES (199, 'f45a4e13-0dfd-48dc-8aef-7654f00140ee', 'TESTSSS', 'TESTSSS', 'moderate', 5, 1, 10, '2023-02-24 14:39:00',
        2, '2023-01-19 14:32:46');
INSERT INTO public.test (id, uuid, name, description, complexity, duration, total_attempt_number, subject_id,
                         expiration_date, max_attempt_number, created) OVERRIDING SYSTEM VALUE
VALUES (18, '3d6078cb-5efc-45f1-9c1f-6c7c57f24bea', 'Santa Fe',
        'Vestibulum ac est lacinia nisi venenatis tristique. Fusce congue, diam id ornare imperdiet, sapien urna pretium nisl, ut volutpat sapien arcu sed augue. Aliquam erat volutpat.',
        'easy', 600, 1, NULL, '2023-01-11 16:47:11', 1, '2023-01-19 14:32:46');
INSERT INTO public.test (id, uuid, name, description, complexity, duration, total_attempt_number, subject_id,
                         expiration_date, max_attempt_number, created) OVERRIDING SYSTEM VALUE
VALUES (176, 'aff3f8bb-aedc-42d0-ac43-045a1c637b96', 'Lepilemur rufescens',
        'Nulla ut erat id mauris vulputate elementum.', 'easy', 900, 1, NULL, '2023-01-11 16:47:11', 1,
        '2023-01-19 14:32:46');
INSERT INTO public.test (id, uuid, name, description, complexity, duration, total_attempt_number, subject_id,
                         expiration_date, max_attempt_number, created) OVERRIDING SYSTEM VALUE
VALUES (10, '647e7820-3dac-4671-a24c-85e1412743fe', 'Jodorowsky Constellation, The (La constellation Jodorowsky)',
        'Duis aliquam convallis nunc. Proin at turpis a pede posuere nonummy. Integer non velit.', 'easy', 600, 1, NULL,
        '2023-01-11 16:47:11', 1, '2023-01-19 14:32:46');
INSERT INTO public.test (id, uuid, name, description, complexity, duration, total_attempt_number, subject_id,
                         expiration_date, max_attempt_number, created) OVERRIDING SYSTEM VALUE
VALUES (106, '879279bf-ce0f-483c-810f-e068a4ebf02b', 'Carphophis sp.', 'Suspendisse ornare consequat lectus.', 'easy',
        900, 1, NULL, '2023-01-11 16:47:11', 1, '2023-01-19 14:32:46');
INSERT INTO public.test (id, uuid, name, description, complexity, duration, total_attempt_number, subject_id,
                         expiration_date, max_attempt_number, created) OVERRIDING SYSTEM VALUE
VALUES (204, '07870aa0-4e51-487d-b501-2b693e0391f2', 'Okey', 'dfgdf', 'moderate', 12, 1, 8, NULL, 10,
        '2023-02-23 16:49:52');
INSERT INTO public.test (id, uuid, name, description, complexity, duration, total_attempt_number, subject_id,
                         expiration_date, max_attempt_number, created) OVERRIDING SYSTEM VALUE
VALUES (195, '7da8b53b-3392-4a1b-96c7-0634dc446a73', 'hard', 'hard', 'hard', 4, 0, 11, '2023-01-25 20:38:00', 4,
        '2023-01-19 14:32:46');
INSERT INTO public.test (id, uuid, name, description, complexity, duration, total_attempt_number, subject_id,
                         expiration_date, max_attempt_number, created) OVERRIDING SYSTEM VALUE
VALUES (200, '095f1724-10b2-4fc3-b068-112553514a4f', 'sdf', 'sdf', 'easy', 45, 52, 1, '2023-01-19 15:24:32.820683', 4,
        '2023-01-19 15:24:33');
INSERT INTO public.test (id, uuid, name, description, complexity, duration, total_attempt_number, subject_id,
                         expiration_date, max_attempt_number, created) OVERRIDING SYSTEM VALUE
VALUES (201, 'b17390e3-04b5-4177-ae06-27ce6e841d47', 'sdf', 'sdf', 'easy', 45, 52, 1, '2023-01-19 15:24:34.954597', 4,
        '2023-01-19 15:24:35');
INSERT INTO public.test (id, uuid, name, description, complexity, duration, total_attempt_number, subject_id,
                         expiration_date, max_attempt_number, created) OVERRIDING SYSTEM VALUE
VALUES (198, '61580978-86e1-4ae3-b73e-8abd0a428f30', 'EEEE', 'EEEE', 'moderate', 58, 1, 8, '2023-01-31 22:49:00', 56,
        '2023-01-19 14:32:46');
INSERT INTO public.test (id, uuid, name, description, complexity, duration, total_attempt_number, subject_id,
                         expiration_date, max_attempt_number, created) OVERRIDING SYSTEM VALUE
VALUES (196, '47e69b11-3967-47b2-829f-5e9ebb22d638', 'SDFSDFSD', 'SDFSDFSD', 'moderate', 7, 0, 4, '2023-01-28 20:42:00',
        5, '2023-01-19 14:32:46');
INSERT INTO public.test (id, uuid, name, description, complexity, duration, total_attempt_number, subject_id,
                         expiration_date, max_attempt_number, created) OVERRIDING SYSTEM VALUE
VALUES (202, '4076c8ae-a28f-4936-b4a5-1470c963f7cc', 'sdfghdfg', 'dfgh', 'moderate', 45, 1, 25, '2023-01-13 21:02:00',
        45, '2023-01-27 21:02:56');
INSERT INTO public.test (id, uuid, name, description, complexity, duration, total_attempt_number, subject_id,
                         expiration_date, max_attempt_number, created) OVERRIDING SYSTEM VALUE
VALUES (197, '43eb1dcd-705c-4cae-b6f8-d792f90f5103', 'rdthy', 'drty', 'hard', 2, 0, 18, NULL, 2, '2023-01-19 14:32:46');
INSERT INTO public.test (id, uuid, name, description, complexity, duration, total_attempt_number, subject_id,
                         expiration_date, max_attempt_number, created) OVERRIDING SYSTEM VALUE
VALUES (59, '664516fb-d657-47a0-812b-25710c934007', 'Lifeboat', 'In congue. Etiam justo. Etiam pretium iaculis justo.',
        'easy', 600, 0, NULL, '2023-01-11 16:47:11', 1, '2023-01-19 14:32:46');
INSERT INTO public.test (id, uuid, name, description, complexity, duration, total_attempt_number, subject_id,
                         expiration_date, max_attempt_number, created) OVERRIDING SYSTEM VALUE
VALUES (15, 'ed27d92e-e03e-4530-afe4-d17632d5e9f5', 'A Promise',
        'Quisque porta volutpat erat. Quisque erat eros, viverra eget, congue eget, semper rutrum, nulla. Nunc purus.',
        'easy', 600, 0, NULL, '2023-01-11 16:47:11', 1, '2023-01-19 14:32:46');
INSERT INTO public.test (id, uuid, name, description, complexity, duration, total_attempt_number, subject_id,
                         expiration_date, max_attempt_number, created) OVERRIDING SYSTEM VALUE
VALUES (207, 'cf1471d0-0d19-4fec-beeb-5aaf5bca276d', 'ADMIN TEST', 'ADMIN TEST', 'hard', 120, 0, 1,
        '2023-03-25 19:37:00', 15, '2023-02-25 14:33:02');
INSERT INTO public.test (id, uuid, name, description, complexity, duration, total_attempt_number, subject_id,
                         expiration_date, max_attempt_number, created) OVERRIDING SYSTEM VALUE
VALUES (17, 'c4f090bf-f8ed-49eb-8dd3-78d4387028bb', 'Thirty Day Princess',
        'Morbi porttitor lorem id ligula. Suspendisse ornare consequat lectus. In est risus, auctor sed, tristique in, tempus sit amet, sem.',
        'easy', 600, 0, NULL, '2023-01-11 16:47:11', 1, '2023-01-19 14:32:46');
INSERT INTO public.test (id, uuid, name, description, complexity, duration, total_attempt_number, subject_id,
                         expiration_date, max_attempt_number, created) OVERRIDING SYSTEM VALUE
VALUES (2, '67ab1b07-c659-4931-bc2b-714e0b412653', 'Snowtown (Snowtown Murders, The)',
        'Sed sagittis. Nam congue, risus semper porta volutpat, quam pede lobortis ligula, sit amet eleifend pede libero quis orci. Nullam molestie nibh in lectus.',
        'easy', 600, 0, NULL, '2023-01-11 16:47:11', 1, '2023-01-19 14:32:46');
INSERT INTO public.test (id, uuid, name, description, complexity, duration, total_attempt_number, subject_id,
                         expiration_date, max_attempt_number, created) OVERRIDING SYSTEM VALUE
VALUES (166, 'e398ebac-6bce-4d71-bd9f-83f61279bb1a', 'Tursiops truncatus',
        'Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Duis faucibus accumsan odio.',
        'easy', 900, 0, NULL, '2023-01-11 16:47:11', 1, '2023-01-19 14:32:46');
INSERT INTO public.test (id, uuid, name, description, complexity, duration, total_attempt_number, subject_id,
                         expiration_date, max_attempt_number, created) OVERRIDING SYSTEM VALUE
VALUES (186, 'c4b8210e-7275-4b48-afa0-5c15baaccacc', 'Lutra canadensis',
        'Fusce lacus purus, aliquet at, feugiat non, pretium quis, lectus.', 'easy', 900, 0, NULL,
        '2023-01-11 16:47:11', 1, '2023-01-19 14:32:46');
INSERT INTO public.test (id, uuid, name, description, complexity, duration, total_attempt_number, subject_id,
                         expiration_date, max_attempt_number, created) OVERRIDING SYSTEM VALUE
VALUES (143, '30c73328-251e-45bd-9012-372c44974730', 'Zosterops pallidus',
        'Donec diam neque, vestibulum eget, vulputate ut, ultrices vel, augue.', 'easy', 900, 1, 3,
        '2023-01-11 16:47:11', 1, '2023-01-19 14:32:46');
INSERT INTO public.test (id, uuid, name, description, complexity, duration, total_attempt_number, subject_id,
                         expiration_date, max_attempt_number, created) OVERRIDING SYSTEM VALUE
VALUES (133, '34c07348-a7d0-405e-83a1-cd2390813e98', 'Ursus americanus',
        'In est risus, auctor sed, tristique in, tempus sit amet, sem.', 'easy', 900, 1, 3, '2023-01-11 16:47:11', 1,
        '2023-01-19 14:32:46');
INSERT INTO public.test (id, uuid, name, description, complexity, duration, total_attempt_number, subject_id,
                         expiration_date, max_attempt_number, created) OVERRIDING SYSTEM VALUE
VALUES (160, '66b09b02-eed2-4866-8789-e8497ea36752', 'Ovis ammon', 'Sed vel enim sit amet nunc viverra dapibus.',
        'easy', 900, 1, 10, '2023-01-11 16:47:11', 1, '2023-01-19 14:32:46');
INSERT INTO public.test (id, uuid, name, description, complexity, duration, total_attempt_number, subject_id,
                         expiration_date, max_attempt_number, created) OVERRIDING SYSTEM VALUE
VALUES (172, 'a1789563-1ead-47ac-8e27-aea7e0c8e2d2', 'Dusicyon thous',
        'Aliquam augue quam, sollicitudin vitae, consectetuer eget, rutrum at, lorem.', 'easy', 900, 0, NULL,
        '2023-01-11 16:47:11', 1, '2023-01-19 14:32:46');
INSERT INTO public.test (id, uuid, name, description, complexity, duration, total_attempt_number, subject_id,
                         expiration_date, max_attempt_number, created) OVERRIDING SYSTEM VALUE
VALUES (165, 'a0838933-de3b-431a-81ba-1d854cdb9e29', 'Butorides striatus', 'Quisque porta volutpat erat.', 'easy', 900,
        0, 5, '2023-01-11 16:47:11', 1, '2023-01-19 14:32:46');
INSERT INTO public.test (id, uuid, name, description, complexity, duration, total_attempt_number, subject_id,
                         expiration_date, max_attempt_number, created) OVERRIDING SYSTEM VALUE
VALUES (164, 'd726872e-3c39-4f5d-bed4-ad3a2105e57b', 'Corvus brachyrhynchos',
        'In est risus, auctor sed, tristique in, tempus sit amet, sem.', 'easy', 900, 0, 4, '2023-01-11 16:47:11', 1,
        '2023-01-19 14:32:46');
INSERT INTO public.test (id, uuid, name, description, complexity, duration, total_attempt_number, subject_id,
                         expiration_date, max_attempt_number, created) OVERRIDING SYSTEM VALUE
VALUES (173, 'b3b4452c-9f49-42cf-a653-f91856b63e60', 'Pseudoleistes virescens',
        'Praesent id massa id nisl venenatis lacinia.', 'easy', 900, 0, 3, '2023-01-11 16:47:11', 1,
        '2023-01-19 14:32:46');
INSERT INTO public.test (id, uuid, name, description, complexity, duration, total_attempt_number, subject_id,
                         expiration_date, max_attempt_number, created) OVERRIDING SYSTEM VALUE
VALUES (175, '0186e3ed-db92-44fb-88af-03c964b51bbf', 'Nyctanassa violacea',
        'Proin leo odio, porttitor id, consequat in, consequat ut, nulla.', 'easy', 900, 0, 5, '2023-01-11 16:47:11', 1,
        '2023-01-19 14:32:46');
INSERT INTO public.test (id, uuid, name, description, complexity, duration, total_attempt_number, subject_id,
                         expiration_date, max_attempt_number, created) OVERRIDING SYSTEM VALUE
VALUES (174, '3b2bf198-8669-46c3-acda-2b3ed4d8b6c6', 'Chamaelo sp.', 'Praesent lectus.', 'easy', 900, 0, 4,
        '2023-01-11 16:47:11', 1, '2023-01-19 14:32:46');
INSERT INTO public.test (id, uuid, name, description, complexity, duration, total_attempt_number, subject_id,
                         expiration_date, max_attempt_number, created) OVERRIDING SYSTEM VALUE
VALUES (161, '25b35ba1-ff87-4fcb-993f-acdc65e6891b', 'Butorides striatus',
        'Vivamus metus arcu, adipiscing molestie, hendrerit at, vulputate vitae, nisl.', 'easy', 900, 0, 1,
        '2023-01-11 16:47:11', 1, '2023-01-19 14:32:46');
INSERT INTO public.test (id, uuid, name, description, complexity, duration, total_attempt_number, subject_id,
                         expiration_date, max_attempt_number, created) OVERRIDING SYSTEM VALUE
VALUES (163, 'd1a54af3-33a8-4e71-beba-c675060c455d', 'Columba livia', 'Proin risus.', 'easy', 900, 0, 3,
        '2023-01-11 16:47:11', 1, '2023-01-19 14:32:46');
INSERT INTO public.test (id, uuid, name, description, complexity, duration, total_attempt_number, subject_id,
                         expiration_date, max_attempt_number, created) OVERRIDING SYSTEM VALUE
VALUES (135, '404caf2b-1f5d-4b5e-b356-ca8ac6bf2c7d', 'Corallus hortulanus cooki', 'Donec ut dolor.', 'easy', 900, 0, 5,
        '2023-01-11 16:47:11', 1, '2023-01-19 14:32:46');
INSERT INTO public.test (id, uuid, name, description, complexity, duration, total_attempt_number, subject_id,
                         expiration_date, max_attempt_number, created) OVERRIDING SYSTEM VALUE
VALUES (134, 'e6a25604-773e-498f-ad6e-5f967c276d38', 'Cygnus atratus', 'Vivamus tortor.', 'easy', 900, 0, 4,
        '2023-01-11 16:47:11', 1, '2023-01-19 14:32:46');
INSERT INTO public.test (id, uuid, name, description, complexity, duration, total_attempt_number, subject_id,
                         expiration_date, max_attempt_number, created) OVERRIDING SYSTEM VALUE
VALUES (185, '0d638b59-cb00-49e3-96bc-c52806260cf0', 'Numida meleagris', 'Donec vitae nisi.', 'easy', 900, 0, 5,
        '2023-01-11 16:47:11', 1, '2023-01-19 14:32:46');
INSERT INTO public.test (id, uuid, name, description, complexity, duration, total_attempt_number, subject_id,
                         expiration_date, max_attempt_number, created) OVERRIDING SYSTEM VALUE
VALUES (184, 'ebbebe4c-a2a8-4395-b82f-6c92aa141e98', 'Phalaropus lobatus', 'Nulla facilisi.', 'easy', 900, 0, 4,
        '2023-01-11 16:47:11', 1, '2023-01-19 14:32:46');
INSERT INTO public.test (id, uuid, name, description, complexity, duration, total_attempt_number, subject_id,
                         expiration_date, max_attempt_number, created) OVERRIDING SYSTEM VALUE
VALUES (187, '2eac049e-71ec-4372-b0c9-f6823c3c29d3', 'Calyptorhynchus magnificus',
        'Cras non velit nec nisi vulputate nonummy.', 'easy', 900, 0, 7, '2023-01-11 16:47:11', 1,
        '2023-01-19 14:32:46');
INSERT INTO public.test (id, uuid, name, description, complexity, duration, total_attempt_number, subject_id,
                         expiration_date, max_attempt_number, created) OVERRIDING SYSTEM VALUE
VALUES (141, '71bfac8c-ee51-4455-b30e-9fbc2daad334', 'Trichosurus vulpecula', 'In blandit ultrices enim.', 'easy', 900,
        0, 1, '2023-01-11 16:47:11', 1, '2023-01-19 14:32:46');
INSERT INTO public.test (id, uuid, name, description, complexity, duration, total_attempt_number, subject_id,
                         expiration_date, max_attempt_number, created) OVERRIDING SYSTEM VALUE
VALUES (140, '90c0a4b8-4a85-45e5-bac4-48676eb298c9', 'Crotalus cerastes',
        'Vivamus in felis eu sapien cursus vestibulum.', 'easy', 900, 0, 10, '2023-01-11 16:47:11', 1,
        '2023-01-19 14:32:46');
INSERT INTO public.test (id, uuid, name, description, complexity, duration, total_attempt_number, subject_id,
                         expiration_date, max_attempt_number, created) OVERRIDING SYSTEM VALUE
VALUES (129, '8da1b241-9ee1-47c3-9aba-da82e4d79e5c', 'Leprocaulinus vipera',
        'Quisque id justo sit amet sapien dignissim vestibulum.', 'easy', 900, 0, 9, '2023-01-11 16:47:11', 1,
        '2023-01-19 14:32:46');
INSERT INTO public.test (id, uuid, name, description, complexity, duration, total_attempt_number, subject_id,
                         expiration_date, max_attempt_number, created) OVERRIDING SYSTEM VALUE
VALUES (131, '4bf548cb-b6a2-4f09-8ba1-efd9d9f020ef', 'Oryx gazella callotis', 'Sed ante.', 'easy', 900, 0, 1,
        '2023-01-11 16:47:11', 1, '2023-01-19 14:32:46');
INSERT INTO public.test (id, uuid, name, description, complexity, duration, total_attempt_number, subject_id,
                         expiration_date, max_attempt_number, created) OVERRIDING SYSTEM VALUE
VALUES (130, '56242d05-a7ee-495b-8854-793171cab6ce', 'Coluber constrictor foxii', 'Quisque porta volutpat erat.',
        'easy', 900, 0, 10, '2023-01-11 16:47:11', 1, '2023-01-19 14:32:46');
INSERT INTO public.test (id, uuid, name, description, complexity, duration, total_attempt_number, subject_id,
                         expiration_date, max_attempt_number, created) OVERRIDING SYSTEM VALUE
VALUES (181, 'e2a84c77-bee0-4be4-acc6-24ff1dec6278', 'Psittacula krameri', 'Curabitur convallis.', 'easy', 900, 0, 1,
        '2023-01-11 16:47:11', 1, '2023-01-19 14:32:46');
INSERT INTO public.test (id, uuid, name, description, complexity, duration, total_attempt_number, subject_id,
                         expiration_date, max_attempt_number, created) OVERRIDING SYSTEM VALUE
VALUES (180, 'bbc1e1aa-c3c2-4af1-85ab-b4206d347c1e', 'Mustela nigripes',
        'In est risus, auctor sed, tristique in, tempus sit amet, sem.', 'easy', 900, 0, 10, '2023-01-11 16:47:11', 1,
        '2023-01-19 14:32:46');
INSERT INTO public.test (id, uuid, name, description, complexity, duration, total_attempt_number, subject_id,
                         expiration_date, max_attempt_number, created) OVERRIDING SYSTEM VALUE
VALUES (183, '5ef70b8b-ac71-4af8-a3d1-21350889db6c', 'Meles meles',
        'Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Nulla dapibus dolor vel est.',
        'easy', 900, 0, 3, '2023-01-11 16:47:11', 1, '2023-01-19 14:32:46');
INSERT INTO public.test (id, uuid, name, description, complexity, duration, total_attempt_number, subject_id,
                         expiration_date, max_attempt_number, created) OVERRIDING SYSTEM VALUE
VALUES (169, 'd9659a88-c4d3-42ec-b7c3-9af85d694ccf', 'Cathartes aura',
        'Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus.', 'easy', 900, 0, 9,
        '2023-01-11 16:47:11', 1, '2023-01-19 14:32:46');
INSERT INTO public.test (id, uuid, name, description, complexity, duration, total_attempt_number, subject_id,
                         expiration_date, max_attempt_number, created) OVERRIDING SYSTEM VALUE
VALUES (162, 'f8e37b25-6b8e-40a0-ba93-e0a84277f7a2', 'Megaderma spasma',
        'Mauris enim leo, rhoncus sed, vestibulum sit amet, cursus id, turpis.', 'easy', 900, 0, NULL,
        '2023-01-11 16:47:11', 1, '2023-01-19 14:32:46');
INSERT INTO public.test (id, uuid, name, description, complexity, duration, total_attempt_number, subject_id,
                         expiration_date, max_attempt_number, created) OVERRIDING SYSTEM VALUE
VALUES (132, '4c975786-0dff-45f9-b6e9-65a36fb7db7f', 'Ardea cinerea', 'Mauris lacinia sapien quis libero.', 'easy', 900,
        0, NULL, '2023-01-11 16:47:11', 1, '2023-01-19 14:32:46');
INSERT INTO public.test (id, uuid, name, description, complexity, duration, total_attempt_number, subject_id,
                         expiration_date, max_attempt_number, created) OVERRIDING SYSTEM VALUE
VALUES (142, '5c8e180c-b4f3-40bb-9972-cfb9f8011eb6', 'Bettongia penicillata', 'In eleifend quam a odio.', 'easy', 900,
        0, NULL, '2023-01-11 16:47:11', 1, '2023-01-19 14:32:46');
INSERT INTO public.test (id, uuid, name, description, complexity, duration, total_attempt_number, subject_id,
                         expiration_date, max_attempt_number, created) OVERRIDING SYSTEM VALUE
VALUES (182, '2c3834b1-0c1f-446a-a773-d609109cebc2', 'Ardea cinerea', 'Aenean fermentum.', 'easy', 900, 0, NULL,
        '2023-01-11 16:47:11', 1, '2023-01-19 14:32:46');
INSERT INTO public.test (id, uuid, name, description, complexity, duration, total_attempt_number, subject_id,
                         expiration_date, max_attempt_number, created) OVERRIDING SYSTEM VALUE
VALUES (203, '19f089c4-8b91-4d03-8a65-89692b4a48b5', 'FIRST', 'FIRST', 'moderate', 10, 5, 25, '2023-03-25 15:55:00', 5,
        '2023-01-30 15:55:47');
INSERT INTO public.test (id, uuid, name, description, complexity, duration, total_attempt_number, subject_id,
                         expiration_date, max_attempt_number, created) OVERRIDING SYSTEM VALUE
VALUES (208, 'fe745b7c-3473-402f-a2d0-2200078c6702', 'TEST1', 'TEST1', 'hard', 145, 10, 1, NULL, 45,
        '2023-02-25 16:06:01');
INSERT INTO public.test (id, uuid, name, description, complexity, duration, total_attempt_number, subject_id,
                         expiration_date, max_attempt_number, created) OVERRIDING SYSTEM VALUE
VALUES (168, '56767eff-133b-463f-8ecd-1373f91d14ad', 'Capra ibex', 'In hac habitasse platea dictumst.', 'easy', 900, 0,
        8, '2023-01-11 16:47:11', 1, '2023-01-19 14:32:46');
INSERT INTO public.test (id, uuid, name, description, complexity, duration, total_attempt_number, subject_id,
                         expiration_date, max_attempt_number, created) OVERRIDING SYSTEM VALUE
VALUES (171, 'cf0e18ea-1cb1-48db-9de2-9c7ef0b0e8bc', 'Tayassu tajacu',
        'Maecenas leo odio, condimentum id, luctus nec, molestie sed, justo.', 'easy', 900, 0, 1, '2023-01-11 16:47:11',
        1, '2023-01-19 14:32:46');
INSERT INTO public.test (id, uuid, name, description, complexity, duration, total_attempt_number, subject_id,
                         expiration_date, max_attempt_number, created) OVERRIDING SYSTEM VALUE
VALUES (170, 'c92c1c7f-0282-4bb0-bf5e-1e8d01925374', 'Acridotheres tristis',
        'Donec ut mauris eget massa tempor convallis.', 'easy', 900, 0, 10, '2023-01-11 16:47:11', 1,
        '2023-01-19 14:32:46');
INSERT INTO public.test (id, uuid, name, description, complexity, duration, total_attempt_number, subject_id,
                         expiration_date, max_attempt_number, created) OVERRIDING SYSTEM VALUE
VALUES (189, '53d3051f-3aa4-4163-8f2f-07fcbae03bc8', 'Ciconia episcopus',
        'Nam ultrices, libero non mattis pulvinar, nulla pede ullamcorper augue, a suscipit nulla elit ac nulla.',
        'easy', 900, 0, 9, '2023-01-11 16:47:11', 1, '2023-01-19 14:32:46');
INSERT INTO public.test (id, uuid, name, description, complexity, duration, total_attempt_number, subject_id,
                         expiration_date, max_attempt_number, created) OVERRIDING SYSTEM VALUE
VALUES (188, '45fe5a9b-9e68-41f9-af1a-bec27d99138d', 'Mycteria leucocephala',
        'Vivamus metus arcu, adipiscing molestie, hendrerit at, vulputate vitae, nisl.', 'easy', 900, 0, 8,
        '2023-01-11 16:47:11', 1, '2023-01-19 14:32:46');
INSERT INTO public.test (id, uuid, name, description, complexity, duration, total_attempt_number, subject_id,
                         expiration_date, max_attempt_number, created) OVERRIDING SYSTEM VALUE
VALUES (190, '0aba1c69-8a87-4160-8684-7f525c0d383a', 'Eremophila alpestris', 'In congue.', 'easy', 900, 0, 10,
        '2023-01-11 16:47:11', 1, '2023-01-19 14:32:46');
INSERT INTO public.test (id, uuid, name, description, complexity, duration, total_attempt_number, subject_id,
                         expiration_date, max_attempt_number, created) OVERRIDING SYSTEM VALUE
VALUES (177, '9604c429-8cf0-479c-a339-0e57b205d982', 'Hymenolaimus malacorhynchus',
        'Vivamus vestibulum sagittis sapien.', 'easy', 900, 0, 7, '2023-01-11 16:47:11', 1, '2023-01-19 14:32:46');
INSERT INTO public.test (id, uuid, name, description, complexity, duration, total_attempt_number, subject_id,
                         expiration_date, max_attempt_number, created) OVERRIDING SYSTEM VALUE
VALUES (179, 'bba8d64a-7098-4368-b883-e6d7970c07a2', 'Merops sp.',
        'Vestibulum quam sapien, varius ut, blandit non, interdum in, ante.', 'easy', 900, 0, 9, '2023-01-11 16:47:11',
        1, '2023-01-19 14:32:46');
INSERT INTO public.test (id, uuid, name, description, complexity, duration, total_attempt_number, subject_id,
                         expiration_date, max_attempt_number, created) OVERRIDING SYSTEM VALUE
VALUES (178, '068ba9f5-b423-4a83-ba5c-c48776b5823a', 'Cacatua tenuirostris', 'Praesent blandit lacinia erat.', 'easy',
        900, 0, 8, '2023-01-11 16:47:11', 1, '2023-01-19 14:32:46');
INSERT INTO public.test (id, uuid, name, description, complexity, duration, total_attempt_number, subject_id,
                         expiration_date, max_attempt_number, created) OVERRIDING SYSTEM VALUE
VALUES (101, '77a1c17c-570e-44cb-8a21-a0fd8a392dd3', 'Junonia genoveua', 'Cras non velit nec nisi vulputate nonummy.',
        'easy', 900, 0, 1, '2023-01-11 16:47:11', 1, '2023-01-19 14:32:46');
INSERT INTO public.test (id, uuid, name, description, complexity, duration, total_attempt_number, subject_id,
                         expiration_date, max_attempt_number, created) OVERRIDING SYSTEM VALUE
VALUES (100, '32280d9c-0bc1-4899-9c35-720b393bf18d', 'D.L. Hughley: Reset',
        'Vestibulum quam sapien, varius ut, blandit non, interdum in, ante. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Duis faucibus accumsan odio. Curabitur convallis.',
        'easy', 600, 0, 14, '2023-01-11 16:47:11', 1, '2023-01-19 14:32:46');
INSERT INTO public.test (id, uuid, name, description, complexity, duration, total_attempt_number, subject_id,
                         expiration_date, max_attempt_number, created) OVERRIDING SYSTEM VALUE
VALUES (103, '22a9c33e-0266-4ddb-83bf-dbe3c5460311', 'Alcelaphus buselaphus cokii',
        'Curabitur at ipsum ac tellus semper interdum.', 'easy', 900, 0, 3, '2023-01-11 16:47:11', 1,
        '2023-01-19 14:32:46');
INSERT INTO public.test (id, uuid, name, description, complexity, duration, total_attempt_number, subject_id,
                         expiration_date, max_attempt_number, created) OVERRIDING SYSTEM VALUE
VALUES (25, 'b46ac800-372b-4959-9007-06e8094b110d', 'The Bronze',
        'Curabitur gravida nisi at nibh. In hac habitasse platea dictumst. Aliquam augue quam, sollicitudin vitae, consectetuer eget, rutrum at, lorem.',
        'easy', 600, 0, 11, '2023-01-11 16:47:11', 1, '2023-01-19 14:32:46');
INSERT INTO public.test (id, uuid, name, description, complexity, duration, total_attempt_number, subject_id,
                         expiration_date, max_attempt_number, created) OVERRIDING SYSTEM VALUE
VALUES (27, 'eed317a9-c820-4dfd-90a1-a304d745abf8', 'Crimes of Fashion',
        'Proin interdum mauris non ligula pellentesque ultrices. Phasellus id sapien in sapien iaculis congue. Vivamus metus arcu, adipiscing molestie, hendrerit at, vulputate vitae, nisl.',
        'easy', 600, 0, 7, '2023-01-11 16:47:11', 1, '2023-01-19 14:32:46');
INSERT INTO public.test (id, uuid, name, description, complexity, duration, total_attempt_number, subject_id,
                         expiration_date, max_attempt_number, created) OVERRIDING SYSTEM VALUE
VALUES (26, '7ddc5f63-420e-4817-af6c-33c6b2c5ea36', 'Break-in',
        'Vestibulum ac est lacinia nisi venenatis tristique. Fusce congue, diam id ornare imperdiet, sapien urna pretium nisl, ut volutpat sapien arcu sed augue. Aliquam erat volutpat.',
        'easy', 600, 0, 11, '2023-01-11 16:47:11', 1, '2023-01-19 14:32:46');
INSERT INTO public.test (id, uuid, name, description, complexity, duration, total_attempt_number, subject_id,
                         expiration_date, max_attempt_number, created) OVERRIDING SYSTEM VALUE
VALUES (109, '0bc2014f-e500-4370-a5db-b311a6e2198f', 'Carduelis pinus', 'Nulla suscipit ligula in lacus.', 'easy', 900,
        0, 9, '2023-01-11 16:47:11', 1, '2023-01-19 14:32:46');
INSERT INTO public.test (id, uuid, name, description, complexity, duration, total_attempt_number, subject_id,
                         expiration_date, max_attempt_number, created) OVERRIDING SYSTEM VALUE
VALUES (108, '69b659b4-3b58-47a5-97a2-2aa8de43c921', 'Otaria flavescens', 'Nunc nisl.', 'easy', 900, 0, 8,
        '2023-01-11 16:47:11', 1, '2023-01-19 14:32:46');
INSERT INTO public.test (id, uuid, name, description, complexity, duration, total_attempt_number, subject_id,
                         expiration_date, max_attempt_number, created) OVERRIDING SYSTEM VALUE
VALUES (111, '0d42e106-1ec2-46be-8f41-e2e2fe2e775f', 'Procyon cancrivorus', 'Sed sagittis.', 'easy', 900, 0, 1,
        '2023-01-11 16:47:11', 1, '2023-01-19 14:32:46');
INSERT INTO public.test (id, uuid, name, description, complexity, duration, total_attempt_number, subject_id,
                         expiration_date, max_attempt_number, created) OVERRIDING SYSTEM VALUE
VALUES (110, '25be456d-7ed3-4811-91b6-d6912acfd469', 'Theropithecus gelada', 'Duis bibendum.', 'easy', 900, 0, 10,
        '2023-01-11 16:47:11', 1, '2023-01-19 14:32:46');
INSERT INTO public.test (id, uuid, name, description, complexity, duration, total_attempt_number, subject_id,
                         expiration_date, max_attempt_number, created) OVERRIDING SYSTEM VALUE
VALUES (97, '3b9c19e8-d748-46c4-86fb-11856ae95e37', 'Blindman',
        'Phasellus sit amet erat. Nulla tempus. Vivamus in felis eu sapien cursus vestibulum.', 'easy', 600, 0, 7,
        '2023-01-11 16:47:11', 1, '2023-01-19 14:32:46');
INSERT INTO public.test (id, uuid, name, description, complexity, duration, total_attempt_number, subject_id,
                         expiration_date, max_attempt_number, created) OVERRIDING SYSTEM VALUE
VALUES (96, '3790a843-5acc-49c8-834e-34bc1f05a22c', 'The Punisher: Dirty Laundry',
        'Donec diam neque, vestibulum eget, vulputate ut, ultrices vel, augue. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Donec pharetra, magna vestibulum aliquet ultrices, erat tortor sollicitudin mi, sit amet lobortis sapien sapien non mi. Integer ac neque.',
        'easy', 600, 0, 18, '2023-01-11 16:47:11', 1, '2023-01-19 14:32:46');
INSERT INTO public.test (id, uuid, name, description, complexity, duration, total_attempt_number, subject_id,
                         expiration_date, max_attempt_number, created) OVERRIDING SYSTEM VALUE
VALUES (99, '3e8af61c-e096-47c1-abad-59056c536299', 'Long, Long Trailer, The',
        'Phasellus sit amet erat. Nulla tempus. Vivamus in felis eu sapien cursus vestibulum.', 'easy', 600, 0, 7,
        '2023-01-11 16:47:11', 1, '2023-01-19 14:32:46');
INSERT INTO public.test (id, uuid, name, description, complexity, duration, total_attempt_number, subject_id,
                         expiration_date, max_attempt_number, created) OVERRIDING SYSTEM VALUE
VALUES (98, 'b1919b8c-abd2-4ac8-a871-2af8356a8f2d', 'Tycoon',
        'Mauris enim leo, rhoncus sed, vestibulum sit amet, cursus id, turpis. Integer aliquet, massa id lobortis convallis, tortor risus dapibus augue, vel accumsan tellus nisi eu orci. Mauris lacinia sapien quis libero.',
        'easy', 600, 0, 17, '2023-01-11 16:47:11', 1, '2023-01-19 14:32:46');
INSERT INTO public.test (id, uuid, name, description, complexity, duration, total_attempt_number, subject_id,
                         expiration_date, max_attempt_number, created) OVERRIDING SYSTEM VALUE
VALUES (21, 'bbebd90d-2064-4619-a93f-874a7b369687', 'Thief of Damascus',
        'Sed ante. Vivamus tortor. Duis mattis egestas metus.', 'easy', 600, 0, 11, '2023-01-11 16:47:11', 1,
        '2023-01-19 14:32:46');
INSERT INTO public.test (id, uuid, name, description, complexity, duration, total_attempt_number, subject_id,
                         expiration_date, max_attempt_number, created) OVERRIDING SYSTEM VALUE
VALUES (20, '1580c92a-39f6-4ef8-bbd0-3dfce6c40ea9', 'Poison', 'In congue. Etiam justo. Etiam pretium iaculis justo.',
        'easy', 600, 0, 9, '2023-01-11 16:47:11', 1, '2023-01-19 14:32:46');
INSERT INTO public.test (id, uuid, name, description, complexity, duration, total_attempt_number, subject_id,
                         expiration_date, max_attempt_number, created) OVERRIDING SYSTEM VALUE
VALUES (23, '65df25b8-1d37-491b-8964-2d5a76ff12a4', 'Foolproof',
        'Phasellus in felis. Donec semper sapien a libero. Nam dui.', 'easy', 600, 0, 1, '2023-01-11 16:47:11', 1,
        '2023-01-19 14:32:46');
INSERT INTO public.test (id, uuid, name, description, complexity, duration, total_attempt_number, subject_id,
                         expiration_date, max_attempt_number, created) OVERRIDING SYSTEM VALUE
VALUES (22, 'd77a5a5e-12fd-4312-9bb9-e2d86425201f', 'Time Tracers',
        'In congue. Etiam justo. Etiam pretium iaculis justo.', 'easy', 600, 0, 18, '2023-01-11 16:47:11', 1,
        '2023-01-19 14:32:46');
INSERT INTO public.test (id, uuid, name, description, complexity, duration, total_attempt_number, subject_id,
                         expiration_date, max_attempt_number, created) OVERRIDING SYSTEM VALUE
VALUES (9, '89c159ca-63c7-4daa-bd93-8c87970fcf32', 'Happy, Happy (Sykt lykkelig)',
        'Curabitur gravida nisi at nibh. In hac habitasse platea dictumst. Aliquam augue quam, sollicitudin vitae, consectetuer eget, rutrum at, lorem.',
        'easy', 600, 0, 11, '2023-01-11 16:47:11', 1, '2023-01-19 14:32:46');
INSERT INTO public.test (id, uuid, name, description, complexity, duration, total_attempt_number, subject_id,
                         expiration_date, max_attempt_number, created) OVERRIDING SYSTEM VALUE
VALUES (8, '87f41d59-e710-45ea-aaa4-f64df259d994', 'Avenging Conscience, The',
        'Quisque id justo sit amet sapien dignissim vestibulum. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Nulla dapibus dolor vel est. Donec odio justo, sollicitudin ut, suscipit a, feugiat et, eros.',
        'easy', 600, 0, 19, '2023-01-11 16:47:11', 1, '2023-01-19 14:32:46');
INSERT INTO public.test (id, uuid, name, description, complexity, duration, total_attempt_number, subject_id,
                         expiration_date, max_attempt_number, created) OVERRIDING SYSTEM VALUE
VALUES (11, 'b64d38b5-2a96-497d-b48f-0d0c7237e955', 'Stepford Wives, The',
        'Integer ac leo. Pellentesque ultrices mattis odio. Donec vitae nisi.', 'easy', 600, 0, 11,
        '2023-01-11 16:47:11', 1, '2023-01-19 14:32:46');
INSERT INTO public.test (id, uuid, name, description, complexity, duration, total_attempt_number, subject_id,
                         expiration_date, max_attempt_number, created) OVERRIDING SYSTEM VALUE
VALUES (29, 'cb9c9b14-b825-467b-8e62-7f7dc0a9f57d', 'Southbounders',
        'Duis aliquam convallis nunc. Proin at turpis a pede posuere nonummy. Integer non velit.', 'easy', 600, 0, 4,
        '2023-01-11 16:47:11', 1, '2023-01-19 14:32:46');
INSERT INTO public.test (id, uuid, name, description, complexity, duration, total_attempt_number, subject_id,
                         expiration_date, max_attempt_number, created) OVERRIDING SYSTEM VALUE
VALUES (102, 'd9847163-4a30-4550-bc7e-390ae1e67a93', 'Lutra canadensis',
        'Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Mauris viverra diam vitae quam.',
        'easy', 900, 0, NULL, '2023-01-11 16:47:11', 1, '2023-01-19 14:32:46');
INSERT INTO public.test (id, uuid, name, description, complexity, duration, total_attempt_number, subject_id,
                         expiration_date, max_attempt_number, created) OVERRIDING SYSTEM VALUE
VALUES (24, '95c3711e-6245-4ac8-997e-32cd23ed3988', 'Birdy',
        'Donec diam neque, vestibulum eget, vulputate ut, ultrices vel, augue. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Donec pharetra, magna vestibulum aliquet ultrices, erat tortor sollicitudin mi, sit amet lobortis sapien sapien non mi. Integer ac neque.',
        'easy', 600, 0, NULL, '2023-01-11 16:47:11', 1, '2023-01-19 14:32:46');
INSERT INTO public.test (id, uuid, name, description, complexity, duration, total_attempt_number, subject_id,
                         expiration_date, max_attempt_number, created) OVERRIDING SYSTEM VALUE
VALUES (28, '7fd5d575-fb28-4857-b3d6-96e2f440e878', 'Two in the Wave',
        'In hac habitasse platea dictumst. Etiam faucibus cursus urna. Ut tellus.', 'easy', 600, 0, 17,
        '2023-01-11 16:47:11', 1, '2023-01-19 14:32:46');
INSERT INTO public.test (id, uuid, name, description, complexity, duration, total_attempt_number, subject_id,
                         expiration_date, max_attempt_number, created) OVERRIDING SYSTEM VALUE
VALUES (31, '7edffee2-edc7-4a8d-86e1-aee68208bf1c', 'This is Martin Bonner',
        'Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Vivamus vestibulum sagittis sapien. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus.',
        'easy', 600, 0, 19, '2023-01-11 16:47:11', 1, '2023-01-19 14:32:46');
INSERT INTO public.test (id, uuid, name, description, complexity, duration, total_attempt_number, subject_id,
                         expiration_date, max_attempt_number, created) OVERRIDING SYSTEM VALUE
VALUES (30, '898498f9-3d50-4908-91d9-2e422833a311', 'Teenage Dirtbag',
        'Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Vivamus vestibulum sagittis sapien. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus.',
        'easy', 600, 0, 7, '2023-01-11 16:47:11', 1, '2023-01-19 14:32:46');
INSERT INTO public.test (id, uuid, name, description, complexity, duration, total_attempt_number, subject_id,
                         expiration_date, max_attempt_number, created) OVERRIDING SYSTEM VALUE
VALUES (19, '492c8923-610f-4967-9062-555009fc8b5c', 'Forever Young',
        'Quisque porta volutpat erat. Quisque erat eros, viverra eget, congue eget, semper rutrum, nulla. Nunc purus.',
        'easy', 600, 0, 14, '2023-01-11 16:47:11', 1, '2023-01-19 14:32:46');
INSERT INTO public.test (id, uuid, name, description, complexity, duration, total_attempt_number, subject_id,
                         expiration_date, max_attempt_number, created) OVERRIDING SYSTEM VALUE
VALUES (69, 'a3708a4a-60e2-46a9-bacb-cf6205bec64b', 'Great Dictator, The',
        'Mauris enim leo, rhoncus sed, vestibulum sit amet, cursus id, turpis. Integer aliquet, massa id lobortis convallis, tortor risus dapibus augue, vel accumsan tellus nisi eu orci. Mauris lacinia sapien quis libero.',
        'easy', 600, 0, 11, '2023-01-11 16:47:11', 1, '2023-01-19 14:32:46');
INSERT INTO public.test (id, uuid, name, description, complexity, duration, total_attempt_number, subject_id,
                         expiration_date, max_attempt_number, created) OVERRIDING SYSTEM VALUE
VALUES (68, '043d7987-b6ad-404c-b3dd-3feb30b45cf5', 'Mon oncle d''Amérique',
        'Sed sagittis. Nam congue, risus semper porta volutpat, quam pede lobortis ligula, sit amet eleifend pede libero quis orci. Nullam molestie nibh in lectus.',
        'easy', 600, 0, 17, '2023-01-11 16:47:11', 1, '2023-01-19 14:32:46');
INSERT INTO public.test (id, uuid, name, description, complexity, duration, total_attempt_number, subject_id,
                         expiration_date, max_attempt_number, created) OVERRIDING SYSTEM VALUE
VALUES (71, 'af82f723-f528-4894-b699-695925000a2e', 'Arena',
        'Maecenas tristique, est et tempus semper, est quam pharetra magna, ac consequat metus sapien ut nunc. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Mauris viverra diam vitae quam. Suspendisse potenti.',
        'easy', 600, 0, 5, '2023-01-11 16:47:11', 1, '2023-01-19 14:32:46');
INSERT INTO public.test (id, uuid, name, description, complexity, duration, total_attempt_number, subject_id,
                         expiration_date, max_attempt_number, created) OVERRIDING SYSTEM VALUE
VALUES (70, '043e27a6-f668-4432-ad75-8d568b7cd7ac', 'Direct from Brooklyn',
        'Nam ultrices, libero non mattis pulvinar, nulla pede ullamcorper augue, a suscipit nulla elit ac nulla. Sed vel enim sit amet nunc viverra dapibus. Nulla suscipit ligula in lacus.',
        'easy', 600, 0, 3, '2023-01-11 16:47:11', 1, '2023-01-19 14:32:46');
INSERT INTO public.test (id, uuid, name, description, complexity, duration, total_attempt_number, subject_id,
                         expiration_date, max_attempt_number, created) OVERRIDING SYSTEM VALUE
VALUES (121, 'a635f354-a423-42eb-8ce1-3bfd8099d925', 'Centrocercus urophasianus',
        'Donec quis orci eget orci vehicula condimentum.', 'easy', 900, 0, 1, '2023-01-11 16:47:11', 1,
        '2023-01-19 14:32:46');
INSERT INTO public.test (id, uuid, name, description, complexity, duration, total_attempt_number, subject_id,
                         expiration_date, max_attempt_number, created) OVERRIDING SYSTEM VALUE
VALUES (120, '60ce3101-38dc-4fc1-8ef2-534cf0e3359d', 'Choriotis kori', 'Quisque porta volutpat erat.', 'easy', 900, 0,
        10, '2023-01-11 16:47:11', 1, '2023-01-19 14:32:46');
INSERT INTO public.test (id, uuid, name, description, complexity, duration, total_attempt_number, subject_id,
                         expiration_date, max_attempt_number, created) OVERRIDING SYSTEM VALUE
VALUES (123, '5d5940ae-1cab-4e60-97cf-9142f0909082', 'Damaliscus lunatus', 'Nam dui.', 'easy', 900, 0, 3,
        '2023-01-11 16:47:11', 1, '2023-01-19 14:32:46');
INSERT INTO public.test (id, uuid, name, description, complexity, duration, total_attempt_number, subject_id,
                         expiration_date, max_attempt_number, created) OVERRIDING SYSTEM VALUE
VALUES (77, '0dbbbc2e-64b7-4024-91c7-61e944370b0e', 'Boat Trip',
        'In sagittis dui vel nisl. Duis ac nibh. Fusce lacus purus, aliquet at, feugiat non, pretium quis, lectus.',
        'easy', 600, 0, 11, '2023-01-11 16:47:11', 1, '2023-01-19 14:32:46');
INSERT INTO public.test (id, uuid, name, description, complexity, duration, total_attempt_number, subject_id,
                         expiration_date, max_attempt_number, created) OVERRIDING SYSTEM VALUE
VALUES (76, 'c8197179-3696-4a85-8188-7706f33a02f5', 'Dr. Gillespie''s New Assistant',
        'Proin interdum mauris non ligula pellentesque ultrices. Phasellus id sapien in sapien iaculis congue. Vivamus metus arcu, adipiscing molestie, hendrerit at, vulputate vitae, nisl.',
        'easy', 600, 0, 4, '2023-01-11 16:47:11', 1, '2023-01-19 14:32:46');
INSERT INTO public.test (id, uuid, name, description, complexity, duration, total_attempt_number, subject_id,
                         expiration_date, max_attempt_number, created) OVERRIDING SYSTEM VALUE
VALUES (79, 'a2bc969c-3984-44e2-ae3a-37dd3a5bdd04', 'Tigger Movie, The',
        'In quis justo. Maecenas rhoncus aliquam lacus. Morbi quis tortor id nulla ultrices aliquet.', 'easy', 600, 0,
        5, '2023-01-11 16:47:11', 1, '2023-01-19 14:32:46');
INSERT INTO public.test (id, uuid, name, description, complexity, duration, total_attempt_number, subject_id,
                         expiration_date, max_attempt_number, created) OVERRIDING SYSTEM VALUE
VALUES (78, '2583e4d6-1a13-4683-bc57-77cb349dd894', 'No Mercy (Yongseoneun Eupda)',
        'Duis bibendum, felis sed interdum venenatis, turpis enim blandit mi, in porttitor pede justo eu massa. Donec dapibus. Duis at velit eu est congue elementum.',
        'easy', 600, 0, 16, '2023-01-11 16:47:11', 1, '2023-01-19 14:32:46');
INSERT INTO public.test (id, uuid, name, description, complexity, duration, total_attempt_number, subject_id,
                         expiration_date, max_attempt_number, created) OVERRIDING SYSTEM VALUE
VALUES (65, '79878ccd-b11f-46fb-ab81-8f4cc22afe03', 'WarGames: The Dead Code',
        'Duis aliquam convallis nunc. Proin at turpis a pede posuere nonummy. Integer non velit.', 'easy', 600, 0, 9,
        '2023-01-11 16:47:11', 1, '2023-01-19 14:32:46');
INSERT INTO public.test (id, uuid, name, description, complexity, duration, total_attempt_number, subject_id,
                         expiration_date, max_attempt_number, created) OVERRIDING SYSTEM VALUE
VALUES (64, 'bc3a4eee-2c7c-4846-aa31-3d8eacda2b41', 'Games of Love and Chance (L''esquive)',
        'Phasellus in felis. Donec semper sapien a libero. Nam dui.', 'easy', 600, 0, 20, '2023-01-11 16:47:11', 1,
        '2023-01-19 14:32:46');
INSERT INTO public.test (id, uuid, name, description, complexity, duration, total_attempt_number, subject_id,
                         expiration_date, max_attempt_number, created) OVERRIDING SYSTEM VALUE
VALUES (67, '84f3c59b-905a-4d35-b03e-96750eb06fcf', 'What If... (An...)',
        'Integer tincidunt ante vel ipsum. Praesent blandit lacinia erat. Vestibulum sed magna at nunc commodo placerat.',
        'easy', 600, 0, 4, '2023-01-11 16:47:11', 1, '2023-01-19 14:32:46');
INSERT INTO public.test (id, uuid, name, description, complexity, duration, total_attempt_number, subject_id,
                         expiration_date, max_attempt_number, created) OVERRIDING SYSTEM VALUE
VALUES (117, '11b21354-dfc5-4508-b8db-2c3c35b8360b', 'Eumetopias jubatus',
        'Morbi sem mauris, laoreet ut, rhoncus aliquet, pulvinar sed, nisl.', 'easy', 900, 0, 7, '2023-01-11 16:47:11',
        1, '2023-01-19 14:32:46');
INSERT INTO public.test (id, uuid, name, description, complexity, duration, total_attempt_number, subject_id,
                         expiration_date, max_attempt_number, created) OVERRIDING SYSTEM VALUE
VALUES (119, '57ffe3ed-fd46-4f17-8742-62be90768b27', 'Francolinus leucoscepus',
        'Cras mi pede, malesuada in, imperdiet et, commodo vulputate, justo.', 'easy', 900, 0, 9, '2023-01-11 16:47:11',
        1, '2023-01-19 14:32:46');
INSERT INTO public.test (id, uuid, name, description, complexity, duration, total_attempt_number, subject_id,
                         expiration_date, max_attempt_number, created) OVERRIDING SYSTEM VALUE
VALUES (118, 'a9aec59c-861a-4227-86df-5674a229b071', 'Haematopus ater', 'In hac habitasse platea dictumst.', 'easy',
        900, 0, 8, '2023-01-11 16:47:11', 1, '2023-01-19 14:32:46');
INSERT INTO public.test (id, uuid, name, description, complexity, duration, total_attempt_number, subject_id,
                         expiration_date, max_attempt_number, created) OVERRIDING SYSTEM VALUE
VALUES (105, '612b1c3f-26f8-4bef-ba24-141cc7055128', 'Ctenophorus ornatus', 'Nunc purus.', 'easy', 900, 0, 5,
        '2023-01-11 16:47:11', 1, '2023-01-19 14:32:46');
INSERT INTO public.test (id, uuid, name, description, complexity, duration, total_attempt_number, subject_id,
                         expiration_date, max_attempt_number, created) OVERRIDING SYSTEM VALUE
VALUES (104, '80312235-0691-467a-9f6f-4a14c8ab2dab', 'Alectura lathami', 'Aliquam non mauris.', 'easy', 900, 0, 4,
        '2023-01-11 16:47:11', 1, '2023-01-19 14:32:46');
INSERT INTO public.test (id, uuid, name, description, complexity, duration, total_attempt_number, subject_id,
                         expiration_date, max_attempt_number, created) OVERRIDING SYSTEM VALUE
VALUES (107, 'd15b8af5-511c-48d9-aaea-269c229deb5e', 'Tachybaptus ruficollis', 'Pellentesque viverra pede ac diam.',
        'easy', 900, 0, 7, '2023-01-11 16:47:11', 1, '2023-01-19 14:32:46');
INSERT INTO public.test (id, uuid, name, description, complexity, duration, total_attempt_number, subject_id,
                         expiration_date, max_attempt_number, created) OVERRIDING SYSTEM VALUE
VALUES (125, '7c19e9d9-e519-45b3-aa9d-c7ca014b09a8', 'Dacelo novaeguineae',
        'Vivamus vel nulla eget eros elementum pellentesque.', 'easy', 900, 0, 5, '2023-01-11 16:47:11', 1,
        '2023-01-19 14:32:46');
INSERT INTO public.test (id, uuid, name, description, complexity, duration, total_attempt_number, subject_id,
                         expiration_date, max_attempt_number, created) OVERRIDING SYSTEM VALUE
VALUES (124, '2d1fd81f-fa1c-4262-bb20-3d320dd022f2', 'Bradypus tridactylus', 'Nam dui.', 'easy', 900, 0, 4,
        '2023-01-11 16:47:11', 1, '2023-01-19 14:32:46');
INSERT INTO public.test (id, uuid, name, description, complexity, duration, total_attempt_number, subject_id,
                         expiration_date, max_attempt_number, created) OVERRIDING SYSTEM VALUE
VALUES (127, '2f69cbc6-6188-44fc-b3cb-24258d0e62e0', 'Bettongia penicillata', 'Fusce posuere felis sed lacus.', 'easy',
        900, 0, 7, '2023-01-11 16:47:11', 1, '2023-01-19 14:32:46');
INSERT INTO public.test (id, uuid, name, description, complexity, duration, total_attempt_number, subject_id,
                         expiration_date, max_attempt_number, created) OVERRIDING SYSTEM VALUE
VALUES (113, 'eb7bdaa7-e48c-4e84-99c7-e9ffdb6a5c4f', 'Acrobates pygmaeus', 'Sed ante.', 'easy', 900, 0, 3,
        '2023-01-11 16:47:11', 1, '2023-01-19 14:32:46');
INSERT INTO public.test (id, uuid, name, description, complexity, duration, total_attempt_number, subject_id,
                         expiration_date, max_attempt_number, created) OVERRIDING SYSTEM VALUE
VALUES (116, '8b639062-132e-4f66-87ca-0d4fb5374b90', 'Macropus robustus',
        'Fusce congue, diam id ornare imperdiet, sapien urna pretium nisl, ut volutpat sapien arcu sed augue.', 'easy',
        900, 0, NULL, '2023-01-11 16:47:11', 1, '2023-01-19 14:32:46');
INSERT INTO public.test (id, uuid, name, description, complexity, duration, total_attempt_number, subject_id,
                         expiration_date, max_attempt_number, created) OVERRIDING SYSTEM VALUE
VALUES (122, 'ecf085b6-83d7-4278-b527-f713c1fb94e9', 'Zalophus californicus',
        'Quisque arcu libero, rutrum ac, lobortis vel, dapibus at, diam.', 'easy', 900, 0, NULL, '2023-01-11 16:47:11',
        1, '2023-01-19 14:32:46');
INSERT INTO public.test (id, uuid, name, description, complexity, duration, total_attempt_number, subject_id,
                         expiration_date, max_attempt_number, created) OVERRIDING SYSTEM VALUE
VALUES (66, 'd6e8290d-d721-4c5c-a8b6-ac739ffe2d6d', 'The Referee',
        'Integer tincidunt ante vel ipsum. Praesent blandit lacinia erat. Vestibulum sed magna at nunc commodo placerat.',
        'easy', 600, 0, NULL, '2023-01-11 16:47:11', 1, '2023-01-19 14:32:46');
INSERT INTO public.test (id, uuid, name, description, complexity, duration, total_attempt_number, subject_id,
                         expiration_date, max_attempt_number, created) OVERRIDING SYSTEM VALUE
VALUES (16, '55a9f5ac-e65f-489d-a096-36a89778b517', 'The Diary of a Teenage Girl',
        'Mauris enim leo, rhoncus sed, vestibulum sit amet, cursus id, turpis. Integer aliquet, massa id lobortis convallis, tortor risus dapibus augue, vel accumsan tellus nisi eu orci. Mauris lacinia sapien quis libero.',
        'hard', 40, 0, 13, '2023-01-11 16:47:11', 10, '2023-01-19 14:32:46');
INSERT INTO public.test (id, uuid, name, description, complexity, duration, total_attempt_number, subject_id,
                         expiration_date, max_attempt_number, created) OVERRIDING SYSTEM VALUE
VALUES (114, 'a438bbc8-b187-4f50-91bc-a7850cb823be', 'Odocoilenaus virginianus', 'Donec semper sapien a libero.',
        'easy', 900, 0, 4, '2023-01-11 16:47:11', 1, '2023-01-19 14:32:46');
INSERT INTO public.test (id, uuid, name, description, complexity, duration, total_attempt_number, subject_id,
                         expiration_date, max_attempt_number, created) OVERRIDING SYSTEM VALUE
VALUES (37, '00fe41af-266e-44b9-a4be-700422655483', 'Savages',
        'Vestibulum quam sapien, varius ut, blandit non, interdum in, ante. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Duis faucibus accumsan odio. Curabitur convallis.',
        'easy', 600, 0, 10, '2023-01-11 16:47:11', 1, '2023-01-19 14:32:46');
INSERT INTO public.test (id, uuid, name, description, complexity, duration, total_attempt_number, subject_id,
                         expiration_date, max_attempt_number, created) OVERRIDING SYSTEM VALUE
VALUES (36, 'eeb991ba-3a47-47d6-be7b-004069065fb6', 'Prisoner of Zenda, The',
        'Maecenas ut massa quis augue luctus tincidunt. Nulla mollis molestie lorem. Quisque ut erat.', 'easy', 600, 0,
        17, '2023-01-11 16:47:11', 1, '2023-01-19 14:32:46');
INSERT INTO public.test (id, uuid, name, description, complexity, duration, total_attempt_number, subject_id,
                         expiration_date, max_attempt_number, created) OVERRIDING SYSTEM VALUE
VALUES (39, '51257a30-8cb3-40a8-8f66-e72cd756ef11', 'Shottas',
        'Suspendisse potenti. In eleifend quam a odio. In hac habitasse platea dictumst.', 'easy', 600, 0, 19,
        '2023-01-11 16:47:11', 1, '2023-01-19 14:32:46');
INSERT INTO public.test (id, uuid, name, description, complexity, duration, total_attempt_number, subject_id,
                         expiration_date, max_attempt_number, created) OVERRIDING SYSTEM VALUE
VALUES (38, 'de5e0b64-c298-412a-a3cf-e92d1dcdc46b', 'Pusher III: I''m the Angel of Death',
        'In quis justo. Maecenas rhoncus aliquam lacus. Morbi quis tortor id nulla ultrices aliquet.', 'easy', 600, 0,
        19, '2023-01-11 16:47:11', 1, '2023-01-19 14:32:46');
INSERT INTO public.test (id, uuid, name, description, complexity, duration, total_attempt_number, subject_id,
                         expiration_date, max_attempt_number, created) OVERRIDING SYSTEM VALUE
VALUES (45, 'bbadfce3-ef1e-4b24-a4c1-b49720da8302', 'Live!',
        'In hac habitasse platea dictumst. Etiam faucibus cursus urna. Ut tellus.', 'easy', 600, 0, 5,
        '2023-01-11 16:47:11', 1, '2023-01-19 14:32:46');
INSERT INTO public.test (id, uuid, name, description, complexity, duration, total_attempt_number, subject_id,
                         expiration_date, max_attempt_number, created) OVERRIDING SYSTEM VALUE
VALUES (44, '5b505aed-6e18-41b9-afbe-7375b7a0924a', 'Rambo (Rambo 4)',
        'Etiam vel augue. Vestibulum rutrum rutrum neque. Aenean auctor gravida sem.', 'easy', 600, 0, 18,
        '2023-01-11 16:47:11', 1, '2023-01-19 14:32:46');
INSERT INTO public.test (id, uuid, name, description, complexity, duration, total_attempt_number, subject_id,
                         expiration_date, max_attempt_number, created) OVERRIDING SYSTEM VALUE
VALUES (47, '000f5041-2ce9-474b-9efd-42c6a5a47186', '36 Hours',
        'Phasellus in felis. Donec semper sapien a libero. Nam dui.', 'easy', 600, 0, 3, '2023-01-11 16:47:11', 1,
        '2023-01-19 14:32:46');
INSERT INTO public.test (id, uuid, name, description, complexity, duration, total_attempt_number, subject_id,
                         expiration_date, max_attempt_number, created) OVERRIDING SYSTEM VALUE
VALUES (46, '05a07c47-7ba9-4881-b5e0-f1c2f9ed0deb', 'Meet the Feebles',
        'Curabitur in libero ut massa volutpat convallis. Morbi odio odio, elementum eu, interdum eu, tincidunt in, leo. Maecenas pulvinar lobortis est.',
        'easy', 600, 0, 10, '2023-01-11 16:47:11', 1, '2023-01-19 14:32:46');
INSERT INTO public.test (id, uuid, name, description, complexity, duration, total_attempt_number, subject_id,
                         expiration_date, max_attempt_number, created) OVERRIDING SYSTEM VALUE
VALUES (33, '9ddbcab3-1895-4dba-9afc-052e8851c0a0', 'Call of the Wild, The',
        'Duis bibendum. Morbi non quam nec dui luctus rutrum. Nulla tellus.', 'easy', 600, 0, 7, '2023-01-11 16:47:11',
        1, '2023-01-19 14:32:46');
INSERT INTO public.test (id, uuid, name, description, complexity, duration, total_attempt_number, subject_id,
                         expiration_date, max_attempt_number, created) OVERRIDING SYSTEM VALUE
VALUES (32, '356551a7-6ebe-4707-8a77-ec9490546a4a', 'Soldier of Orange (a.k.a. Survival Run) (Soldaat van Oranje)',
        'Fusce consequat. Nulla nisl. Nunc nisl.', 'easy', 600, 0, 19, '2023-01-11 16:47:11', 1, '2023-01-19 14:32:46');
INSERT INTO public.test (id, uuid, name, description, complexity, duration, total_attempt_number, subject_id,
                         expiration_date, max_attempt_number, created) OVERRIDING SYSTEM VALUE
VALUES (35, 'f036fffc-804b-4596-9b54-a3da41056755', 'Business of Being Born, The',
        'Maecenas ut massa quis augue luctus tincidunt. Nulla mollis molestie lorem. Quisque ut erat.', 'easy', 600, 0,
        3, '2023-01-11 16:47:11', 1, '2023-01-19 14:32:46');
INSERT INTO public.test (id, uuid, name, description, complexity, duration, total_attempt_number, subject_id,
                         expiration_date, max_attempt_number, created) OVERRIDING SYSTEM VALUE
VALUES (5, '76eb789c-e77e-434f-b610-71174c282573', 'Afflicted, The',
        'Curabitur in libero ut massa volutpat convallis. Morbi odio odio, elementum eu, interdum eu, tincidunt in, leo. Maecenas pulvinar lobortis est.',
        'easy', 600, 0, 8, '2023-01-11 16:47:11', 1, '2023-01-19 14:32:46');
INSERT INTO public.test (id, uuid, name, description, complexity, duration, total_attempt_number, subject_id,
                         expiration_date, max_attempt_number, created) OVERRIDING SYSTEM VALUE
VALUES (4, '477e9709-5038-4642-b1cc-857d1ad0b1d7', 'Mercy Streets',
        'In sagittis dui vel nisl. Duis ac nibh. Fusce lacus purus, aliquet at, feugiat non, pretium quis, lectus.',
        'easy', 600, 0, 1, '2023-01-11 16:47:11', 1, '2023-01-19 14:32:46');
INSERT INTO public.test (id, uuid, name, description, complexity, duration, total_attempt_number, subject_id,
                         expiration_date, max_attempt_number, created) OVERRIDING SYSTEM VALUE
VALUES (7, 'cb314620-616b-4f6d-adf5-95394393288a', 'Callas Forever',
        'Sed ante. Vivamus tortor. Duis mattis egestas metus.', 'easy', 600, 0, 11, '2023-01-11 16:47:11', 1,
        '2023-01-19 14:32:46');
INSERT INTO public.test (id, uuid, name, description, complexity, duration, total_attempt_number, subject_id,
                         expiration_date, max_attempt_number, created) OVERRIDING SYSTEM VALUE
VALUES (57, '83e43b97-0a2d-47ff-a20d-c3f0f669184d', 'Boxer, The',
        'Fusce posuere felis sed lacus. Morbi sem mauris, laoreet ut, rhoncus aliquet, pulvinar sed, nisl. Nunc rhoncus dui vel sem.',
        'easy', 600, 0, 16, '2023-01-11 16:47:11', 1, '2023-01-19 14:32:46');
INSERT INTO public.test (id, uuid, name, description, complexity, duration, total_attempt_number, subject_id,
                         expiration_date, max_attempt_number, created) OVERRIDING SYSTEM VALUE
VALUES (56, 'ec148745-a31c-4ab0-a523-45b3655296f2', 'Full House (O. Henry''s Full House)',
        'Integer tincidunt ante vel ipsum. Praesent blandit lacinia erat. Vestibulum sed magna at nunc commodo placerat.',
        'easy', 600, 0, 10, '2023-01-11 16:47:11', 1, '2023-01-19 14:32:46');
INSERT INTO public.test (id, uuid, name, description, complexity, duration, total_attempt_number, subject_id,
                         expiration_date, max_attempt_number, created) OVERRIDING SYSTEM VALUE
VALUES (58, '00ca1cbe-55ca-4c27-8554-4f28f1cefb9e', 'Little Criminals',
        'Nulla ut erat id mauris vulputate elementum. Nullam varius. Nulla facilisi.', 'easy', 600, 0, 16,
        '2023-01-11 16:47:11', 1, '2023-01-19 14:32:46');
INSERT INTO public.test (id, uuid, name, description, complexity, duration, total_attempt_number, subject_id,
                         expiration_date, max_attempt_number, created) OVERRIDING SYSTEM VALUE
VALUES (14, 'de9a1bf2-b4d1-4d2e-b2a2-74b9fc218547', 'Millie',
        'Proin eu mi. Nulla ac enim. In tempor, turpis nec euismod scelerisque, quam turpis adipiscing lorem, vitae mattis nibh ligula nec sem.',
        'easy', 600, 0, 13, '2023-01-11 16:47:11', 1, '2023-01-19 14:32:46');
INSERT INTO public.test (id, uuid, name, description, complexity, duration, total_attempt_number, subject_id,
                         expiration_date, max_attempt_number, created) OVERRIDING SYSTEM VALUE
VALUES (1, '384d2fa8-037c-4922-afed-0199be167ed0', 'Thirty-Five Something (Tout pour plaire)',
        'Proin eu mi. Nulla ac enim. In tempor, turpis nec euismod scelerisque, quam turpis adipiscing lorem, vitae mattis nibh ligula nec sem.',
        'easy', 600, 0, 9, '2023-01-11 16:47:11', 1, '2023-01-19 14:32:46');
INSERT INTO public.test (id, uuid, name, description, complexity, duration, total_attempt_number, subject_id,
                         expiration_date, max_attempt_number, created) OVERRIDING SYSTEM VALUE
VALUES (3, 'e107be5c-5554-4f00-bcab-b73bd45f7bd0', 'One Lucky Elephant',
        'Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Vivamus vestibulum sagittis sapien. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus.',
        'easy', 600, 0, 17, '2023-01-11 16:47:11', 1, '2023-01-19 14:32:46');
INSERT INTO public.test (id, uuid, name, description, complexity, duration, total_attempt_number, subject_id,
                         expiration_date, max_attempt_number, created) OVERRIDING SYSTEM VALUE
VALUES (53, '5a3dbc15-fd96-4fa9-a472-7c9e476d824c', 'The Karen Carpenter Story',
        'Quisque porta volutpat erat. Quisque erat eros, viverra eget, congue eget, semper rutrum, nulla. Nunc purus.',
        'easy', 600, 0, 11, '2023-01-11 16:47:11', 1, '2023-01-19 14:32:46');
INSERT INTO public.test (id, uuid, name, description, complexity, duration, total_attempt_number, subject_id,
                         expiration_date, max_attempt_number, created) OVERRIDING SYSTEM VALUE
VALUES (52, 'd5817f5f-8728-4394-a220-a67216090c2a', 'Snowriders',
        'Nulla ut erat id mauris vulputate elementum. Nullam varius. Nulla facilisi.', 'easy', 600, 0, 19,
        '2023-01-11 16:47:11', 1, '2023-01-19 14:32:46');
INSERT INTO public.test (id, uuid, name, description, complexity, duration, total_attempt_number, subject_id,
                         expiration_date, max_attempt_number, created) OVERRIDING SYSTEM VALUE
VALUES (55, '19ad5f6d-a7d8-4ae6-a657-f09d2a898949', 'Noise',
        'Etiam vel augue. Vestibulum rutrum rutrum neque. Aenean auctor gravida sem.', 'easy', 600, 0, 9,
        '2023-01-11 16:47:11', 1, '2023-01-19 14:32:46');
INSERT INTO public.test (id, uuid, name, description, complexity, duration, total_attempt_number, subject_id,
                         expiration_date, max_attempt_number, created) OVERRIDING SYSTEM VALUE
VALUES (41, 'f7a946ca-40b7-4618-8fea-1e55cb61f735', 'The Woman in Black 2: Angel of Death',
        'Integer tincidunt ante vel ipsum. Praesent blandit lacinia erat. Vestibulum sed magna at nunc commodo placerat.',
        'easy', 600, 0, 3, '2023-01-11 16:47:11', 1, '2023-01-19 14:32:46');
INSERT INTO public.test (id, uuid, name, description, complexity, duration, total_attempt_number, subject_id,
                         expiration_date, max_attempt_number, created) OVERRIDING SYSTEM VALUE
VALUES (54, '9cb3f9bf-42ce-49c7-a3d9-4645a39aca2e', 'Rated X: A Journey Through Porn',
        'Pellentesque at nulla. Suspendisse potenti. Cras in purus eu magna vulputate luctus.', 'easy', 600, 0, NULL,
        '2023-01-11 16:47:11', 1, '2023-01-19 14:32:46');
INSERT INTO public.test (id, uuid, name, description, complexity, duration, total_attempt_number, subject_id,
                         expiration_date, max_attempt_number, created) OVERRIDING SYSTEM VALUE
VALUES (13, '8d360920-4747-4aad-9723-4869b37e6b93', 'God Save My Shoes',
        'In sagittis dui vel nisl. Duis ac nibh. Fusce lacus purus, aliquet at, feugiat non, pretium quis, lectus.',
        'easy', 600, 0, NULL, '2023-01-11 16:47:11', 1, '2023-01-19 14:32:46');
INSERT INTO public.test (id, uuid, name, description, complexity, duration, total_attempt_number, subject_id,
                         expiration_date, max_attempt_number, created) OVERRIDING SYSTEM VALUE
VALUES (6, '9745fc43-2e3d-4fb8-b6ca-101c175c2514', 'Killer, The (Die xue shuang xiong)',
        'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Proin risus. Praesent lectus.', 'easy', 600, 0, NULL,
        '2023-01-11 16:47:11', 1, '2023-01-19 14:32:46');
INSERT INTO public.test (id, uuid, name, description, complexity, duration, total_attempt_number, subject_id,
                         expiration_date, max_attempt_number, created) OVERRIDING SYSTEM VALUE
VALUES (12, '0d42af5f-ac6f-44fa-8eed-7030dad505bc', 'Sunday in the Country, A (Un dimanche à la campagne)',
        'Praesent blandit. Nam nulla. Integer pede justo, lacinia eget, tincidunt eget, tempus vel, pede.', 'easy', 600,
        0, NULL, '2023-01-11 16:47:11', 1, '2023-01-19 14:32:46');
INSERT INTO public.test (id, uuid, name, description, complexity, duration, total_attempt_number, subject_id,
                         expiration_date, max_attempt_number, created) OVERRIDING SYSTEM VALUE
VALUES (112, 'caa2028e-ee02-4951-bd28-6b7194981211', 'Manouria emys', 'Vestibulum rutrum rutrum neque.', 'easy', 900, 0,
        NULL, '2023-01-11 16:47:11', 1, '2023-01-19 14:32:46');
INSERT INTO public.test (id, uuid, name, description, complexity, duration, total_attempt_number, subject_id,
                         expiration_date, max_attempt_number, created) OVERRIDING SYSTEM VALUE
VALUES (34, '05830c43-942d-4626-b3ca-50018bd0a0da', 'Purpose',
        'Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Vivamus vestibulum sagittis sapien. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus.',
        'easy', 600, 0, NULL, '2023-01-11 16:47:11', 1, '2023-01-19 14:32:46');
INSERT INTO public.test (id, uuid, name, description, complexity, duration, total_attempt_number, subject_id,
                         expiration_date, max_attempt_number, created) OVERRIDING SYSTEM VALUE
VALUES (40, '92b9c59c-857e-4b41-ae9e-b18b4fa31293', 'Man in the White Suit, The',
        'In sagittis dui vel nisl. Duis ac nibh. Fusce lacus purus, aliquet at, feugiat non, pretium quis, lectus.',
        'easy', 600, 0, 17, '2023-01-11 16:47:11', 1, '2023-01-19 14:32:46');
INSERT INTO public.test (id, uuid, name, description, complexity, duration, total_attempt_number, subject_id,
                         expiration_date, max_attempt_number, created) OVERRIDING SYSTEM VALUE
VALUES (43, '9a250f6a-e04a-4320-85d7-6138decbdfcd', 'Cheerleader Camp',
        'Praesent blandit. Nam nulla. Integer pede justo, lacinia eget, tincidunt eget, tempus vel, pede.', 'easy', 600,
        0, 1, '2023-01-11 16:47:11', 1, '2023-01-19 14:32:46');
INSERT INTO public.test (id, uuid, name, description, complexity, duration, total_attempt_number, subject_id,
                         expiration_date, max_attempt_number, created) OVERRIDING SYSTEM VALUE
VALUES (42, '3925f417-b548-4e38-b70c-10f0152ca44b', 'Place in the Sun, A',
        'Aenean fermentum. Donec ut mauris eget massa tempor convallis. Nulla neque libero, convallis eget, eleifend luctus, ultricies eu, nibh.',
        'easy', 600, 0, 17, '2023-01-11 16:47:11', 1, '2023-01-19 14:32:46');
INSERT INTO public.test (id, uuid, name, description, complexity, duration, total_attempt_number, subject_id,
                         expiration_date, max_attempt_number, created) OVERRIDING SYSTEM VALUE
VALUES (61, '7a122119-b140-489a-99ed-8d0bc24e2e31', 'Lone Star',
        'Duis consequat dui nec nisi volutpat eleifend. Donec ut dolor. Morbi vel lectus in quam fringilla rhoncus.',
        'easy', 600, 0, 16, '2023-01-11 16:47:11', 1, '2023-01-19 14:32:46');
INSERT INTO public.test (id, uuid, name, description, complexity, duration, total_attempt_number, subject_id,
                         expiration_date, max_attempt_number, created) OVERRIDING SYSTEM VALUE
VALUES (60, 'aa062088-d46c-4157-aada-64d7282a644b', 'Curiosity of Chance, The',
        'Curabitur at ipsum ac tellus semper interdum. Mauris ullamcorper purus sit amet nulla. Quisque arcu libero, rutrum ac, lobortis vel, dapibus at, diam.',
        'easy', 600, 0, 19, '2023-01-11 16:47:11', 1, '2023-01-19 14:32:46');
INSERT INTO public.test (id, uuid, name, description, complexity, duration, total_attempt_number, subject_id,
                         expiration_date, max_attempt_number, created) OVERRIDING SYSTEM VALUE
VALUES (63, '440262f2-db72-4500-8076-19e0fa2cc7a9', 'Eve and the Fire Horse',
        'Aenean lectus. Pellentesque eget nunc. Donec quis orci eget orci vehicula condimentum.', 'easy', 600, 0, 4,
        '2023-01-11 16:47:11', 1, '2023-01-19 14:32:46');
INSERT INTO public.test (id, uuid, name, description, complexity, duration, total_attempt_number, subject_id,
                         expiration_date, max_attempt_number, created) OVERRIDING SYSTEM VALUE
VALUES (49, '5ff60ff6-a9f6-4a51-8248-2839047af14a', 'Great Directors',
        'Maecenas tristique, est et tempus semper, est quam pharetra magna, ac consequat metus sapien ut nunc. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Mauris viverra diam vitae quam. Suspendisse potenti.',
        'easy', 600, 0, 7, '2023-01-11 16:47:11', 1, '2023-01-19 14:32:46');
INSERT INTO public.test (id, uuid, name, description, complexity, duration, total_attempt_number, subject_id,
                         expiration_date, max_attempt_number, created) OVERRIDING SYSTEM VALUE
VALUES (48, '45733b5c-a928-4eb9-98e4-1e51435c19e0', 'Pumzi',
        'Nullam sit amet turpis elementum ligula vehicula consequat. Morbi a ipsum. Integer a nibh.', 'easy', 600, 0,
        11, '2023-01-11 16:47:11', 1, '2023-01-19 14:32:46');
INSERT INTO public.test (id, uuid, name, description, complexity, duration, total_attempt_number, subject_id,
                         expiration_date, max_attempt_number, created) OVERRIDING SYSTEM VALUE
VALUES (51, '6cbc7d59-506c-4115-ba98-08c673c9c38c', 'Oxy-Morons',
        'Sed ante. Vivamus tortor. Duis mattis egestas metus.', 'easy', 600, 0, 7, '2023-01-11 16:47:11', 1,
        '2023-01-19 14:32:46');
INSERT INTO public.test (id, uuid, name, description, complexity, duration, total_attempt_number, subject_id,
                         expiration_date, max_attempt_number, created) OVERRIDING SYSTEM VALUE
VALUES (148, '9b71be07-aacd-49d1-8c77-d9fcf2c8a665', 'Cyrtodactylus louisiadensis',
        'Praesent id massa id nisl venenatis lacinia.', 'easy', 900, 0, 8, '2023-01-11 16:47:11', 1,
        '2023-01-19 14:32:46');
INSERT INTO public.test (id, uuid, name, description, complexity, duration, total_attempt_number, subject_id,
                         expiration_date, max_attempt_number, created) OVERRIDING SYSTEM VALUE
VALUES (149, 'dacfb423-5ac6-4429-a54c-9469c8761fd7', 'Papilio canadensis', 'In hac habitasse platea dictumst.', 'easy',
        900, 0, 9, '2023-01-11 16:47:11', 1, '2023-01-19 14:32:46');
INSERT INTO public.test (id, uuid, name, description, complexity, duration, total_attempt_number, subject_id,
                         expiration_date, max_attempt_number, created) OVERRIDING SYSTEM VALUE
VALUES (154, 'b48dd339-7883-4176-9f24-fd224b60fee9', 'Cochlearius cochlearius', 'In hac habitasse platea dictumst.',
        'easy', 900, 0, 4, '2023-01-11 16:47:11', 1, '2023-01-19 14:32:46');
INSERT INTO public.test (id, uuid, name, description, complexity, duration, total_attempt_number, subject_id,
                         expiration_date, max_attempt_number, created) OVERRIDING SYSTEM VALUE
VALUES (155, '18c98941-5488-4825-857c-f9f6a29a5428', 'Lemur catta',
        'Morbi odio odio, elementum eu, interdum eu, tincidunt in, leo.', 'easy', 900, 0, 5, '2023-01-11 16:47:11', 1,
        '2023-01-19 14:32:46');
INSERT INTO public.test (id, uuid, name, description, complexity, duration, total_attempt_number, subject_id,
                         expiration_date, max_attempt_number, created) OVERRIDING SYSTEM VALUE
VALUES (88, 'fa94320f-071c-4387-b02e-8f97c957e452', 'The Anomaly',
        'Morbi porttitor lorem id ligula. Suspendisse ornare consequat lectus. In est risus, auctor sed, tristique in, tempus sit amet, sem.',
        'easy', 600, 0, 14, '2023-01-11 16:47:11', 1, '2023-01-19 14:32:46');
INSERT INTO public.test (id, uuid, name, description, complexity, duration, total_attempt_number, subject_id,
                         expiration_date, max_attempt_number, created) OVERRIDING SYSTEM VALUE
VALUES (91, '1853d64c-1f8d-4b16-9669-f98e5c1ad346', 'Wild in the Streets',
        'Duis bibendum. Morbi non quam nec dui luctus rutrum. Nulla tellus.', 'easy', 600, 0, 11, '2023-01-11 16:47:11',
        1, '2023-01-19 14:32:46');
INSERT INTO public.test (id, uuid, name, description, complexity, duration, total_attempt_number, subject_id,
                         expiration_date, max_attempt_number, created) OVERRIDING SYSTEM VALUE
VALUES (137, '18a1888d-66f5-4846-8500-3b9b21b0a2f4', 'Otocyon megalotis', 'Nam nulla.', 'easy', 900, 0, 7,
        '2023-01-11 16:47:11', 1, '2023-01-19 14:32:46');
INSERT INTO public.test (id, uuid, name, description, complexity, duration, total_attempt_number, subject_id,
                         expiration_date, max_attempt_number, created) OVERRIDING SYSTEM VALUE
VALUES (150, '7cd4e7b5-9b4d-4750-a686-88aeaf6f076d', 'Varanus sp.', 'Fusce consequat.', 'easy', 900, 0, 10,
        '2023-01-11 16:47:11', 1, '2023-01-19 14:32:46');
INSERT INTO public.test (id, uuid, name, description, complexity, duration, total_attempt_number, subject_id,
                         expiration_date, max_attempt_number, created) OVERRIDING SYSTEM VALUE
VALUES (151, '25509551-c607-4e9f-9a28-22b73cfb1c39', 'Varanus sp.', 'In congue.', 'easy', 900, 0, 1,
        '2023-01-11 16:47:11', 1, '2023-01-19 14:32:46');
INSERT INTO public.test (id, uuid, name, description, complexity, duration, total_attempt_number, subject_id,
                         expiration_date, max_attempt_number, created) OVERRIDING SYSTEM VALUE
VALUES (153, '84c5bd4e-ac70-4785-8d6b-301d3388efce', 'Tauraco porphyrelophus', 'Aenean auctor gravida sem.', 'easy',
        900, 0, 3, '2023-01-11 16:47:11', 1, '2023-01-19 14:32:46');
INSERT INTO public.test (id, uuid, name, description, complexity, duration, total_attempt_number, subject_id,
                         expiration_date, max_attempt_number, created) OVERRIDING SYSTEM VALUE
VALUES (85, 'a5e78ef6-c1c9-4e2f-85a3-c99e103bdb8a', 'MirrorMask',
        'Integer ac leo. Pellentesque ultrices mattis odio. Donec vitae nisi.', 'easy', 600, 0, 16,
        '2023-01-11 16:47:11', 1, '2023-01-19 14:32:46');
INSERT INTO public.test (id, uuid, name, description, complexity, duration, total_attempt_number, subject_id,
                         expiration_date, max_attempt_number, created) OVERRIDING SYSTEM VALUE
VALUES (84, 'ca3e4c93-8dc3-4692-8d97-59d9abe34f78', 'Ikiru',
        'Nullam porttitor lacus at turpis. Donec posuere metus vitae ipsum. Aliquam non mauris.', 'easy', 600, 0, 8,
        '2023-01-11 16:47:11', 1, '2023-01-19 14:32:46');
INSERT INTO public.test (id, uuid, name, description, complexity, duration, total_attempt_number, subject_id,
                         expiration_date, max_attempt_number, created) OVERRIDING SYSTEM VALUE
VALUES (86, '1acae48e-0d32-4df9-8817-d7c10f1e6238', 'Secret Window',
        'Duis bibendum, felis sed interdum venenatis, turpis enim blandit mi, in porttitor pede justo eu massa. Donec dapibus. Duis at velit eu est congue elementum.',
        'easy', 600, 0, 4, '2023-01-11 16:47:11', 1, '2023-01-19 14:32:46');
INSERT INTO public.test (id, uuid, name, description, complexity, duration, total_attempt_number, subject_id,
                         expiration_date, max_attempt_number, created) OVERRIDING SYSTEM VALUE
VALUES (73, '4f6bf015-b26c-48ad-812e-3cf9480a93e4', 'Madagascar 3: Europe''s Most Wanted',
        'In hac habitasse platea dictumst. Morbi vestibulum, velit id pretium iaculis, diam erat fermentum justo, nec condimentum neque sapien placerat ante. Nulla justo.',
        'easy', 600, 0, 14, '2023-01-11 16:47:11', 1, '2023-01-19 14:32:46');
INSERT INTO public.test (id, uuid, name, description, complexity, duration, total_attempt_number, subject_id,
                         expiration_date, max_attempt_number, created) OVERRIDING SYSTEM VALUE
VALUES (72, '3a98364c-c32b-4470-aeee-8ae6e2f75164', 'Love Crime (Crime d''amour)',
        'Morbi non lectus. Aliquam sit amet diam in magna bibendum imperdiet. Nullam orci pede, venenatis non, sodales sed, tincidunt eu, felis.',
        'easy', 600, 0, 8, '2023-01-11 16:47:11', 1, '2023-01-19 14:32:46');
INSERT INTO public.test (id, uuid, name, description, complexity, duration, total_attempt_number, subject_id,
                         expiration_date, max_attempt_number, created) OVERRIDING SYSTEM VALUE
VALUES (75, '73160be9-dc2a-41e2-9f06-5a35ee3288e7', 'Maximum Conviction',
        'Praesent blandit. Nam nulla. Integer pede justo, lacinia eget, tincidunt eget, tempus vel, pede.', 'easy', 600,
        0, 1, '2023-01-11 16:47:11', 1, '2023-01-19 14:32:46');
INSERT INTO public.test (id, uuid, name, description, complexity, duration, total_attempt_number, subject_id,
                         expiration_date, max_attempt_number, created) OVERRIDING SYSTEM VALUE
VALUES (74, '0c119ead-79a2-4e33-ad73-5051ec7ad6d2', 'City of Hope',
        'Curabitur at ipsum ac tellus semper interdum. Mauris ullamcorper purus sit amet nulla. Quisque arcu libero, rutrum ac, lobortis vel, dapibus at, diam.',
        'easy', 600, 0, 14, '2023-01-11 16:47:11', 1, '2023-01-19 14:32:46');
INSERT INTO public.test (id, uuid, name, description, complexity, duration, total_attempt_number, subject_id,
                         expiration_date, max_attempt_number, created) OVERRIDING SYSTEM VALUE
VALUES (93, 'db176d4f-dac0-4ec8-a709-32fb8fd19572', 'Rare Birds',
        'Nullam sit amet turpis elementum ligula vehicula consequat. Morbi a ipsum. Integer a nibh.', 'easy', 600, 0,
        14, '2023-01-11 16:47:11', 1, '2023-01-19 14:32:46');
INSERT INTO public.test (id, uuid, name, description, complexity, duration, total_attempt_number, subject_id,
                         expiration_date, max_attempt_number, created) OVERRIDING SYSTEM VALUE
VALUES (92, '60904566-ab9f-44c1-b4c5-51016f3a8a2a', 'Reckoning, The',
        'In congue. Etiam justo. Etiam pretium iaculis justo.', 'easy', 600, 0, 19, '2023-01-11 16:47:11', 1,
        '2023-01-19 14:32:46');
INSERT INTO public.test (id, uuid, name, description, complexity, duration, total_attempt_number, subject_id,
                         expiration_date, max_attempt_number, created) OVERRIDING SYSTEM VALUE
VALUES (95, 'dd63ae14-05f4-4ad7-9116-0a64491a4a0d', 'Momma''s Man',
        'In quis justo. Maecenas rhoncus aliquam lacus. Morbi quis tortor id nulla ultrices aliquet.', 'easy', 600, 0,
        8, '2023-01-11 16:47:11', 1, '2023-01-19 14:32:46');
INSERT INTO public.test (id, uuid, name, description, complexity, duration, total_attempt_number, subject_id,
                         expiration_date, max_attempt_number, created) OVERRIDING SYSTEM VALUE
VALUES (94, 'd12345e4-7b2b-44ca-9bff-f49a0d750d4d', '13B',
        'In hac habitasse platea dictumst. Morbi vestibulum, velit id pretium iaculis, diam erat fermentum justo, nec condimentum neque sapien placerat ante. Nulla justo.',
        'easy', 600, 0, 11, '2023-01-11 16:47:11', 1, '2023-01-19 14:32:46');
INSERT INTO public.test (id, uuid, name, description, complexity, duration, total_attempt_number, subject_id,
                         expiration_date, max_attempt_number, created) OVERRIDING SYSTEM VALUE
VALUES (62, 'e9f99ec1-6fce-4090-83a3-e28fcdc56ca6', 'Dinotopia', 'Sed ante. Vivamus tortor. Duis mattis egestas metus.',
        'easy', 600, 0, NULL, '2023-01-11 16:47:11', 1, '2023-01-19 14:32:46');
INSERT INTO public.test (id, uuid, name, description, complexity, duration, total_attempt_number, subject_id,
                         expiration_date, max_attempt_number, created) OVERRIDING SYSTEM VALUE
VALUES (50, 'ceb75544-ce65-4559-a701-3b7f2b2ad9d0', '112 Weddings',
        'Nullam porttitor lacus at turpis. Donec posuere metus vitae ipsum. Aliquam non mauris.', 'easy', 600, 0, NULL,
        '2023-01-11 16:47:11', 1, '2023-01-19 14:32:46');
INSERT INTO public.test (id, uuid, name, description, complexity, duration, total_attempt_number, subject_id,
                         expiration_date, max_attempt_number, created) OVERRIDING SYSTEM VALUE
VALUES (152, '55166bc1-3ebd-47c8-b5e1-f7d589317f52', 'Bubulcus ibis', 'Fusce consequat.', 'easy', 900, 0, NULL,
        '2023-01-11 16:47:11', 1, '2023-01-19 14:32:46');
INSERT INTO public.test (id, uuid, name, description, complexity, duration, total_attempt_number, subject_id,
                         expiration_date, max_attempt_number, created) OVERRIDING SYSTEM VALUE
VALUES (80, 'c59ecce7-a13d-469e-800b-b00a9f0aca3a', 'Dragon Missile, The (Fei long zhan)',
        'Aenean fermentum. Donec ut mauris eget massa tempor convallis. Nulla neque libero, convallis eget, eleifend luctus, ultricies eu, nibh.',
        'easy', 600, 0, 11, '2023-01-11 16:47:11', 1, '2023-01-19 14:32:46');
INSERT INTO public.test (id, uuid, name, description, complexity, duration, total_attempt_number, subject_id,
                         expiration_date, max_attempt_number, created) OVERRIDING SYSTEM VALUE
VALUES (83, '7ed8c0a9-1780-4a6f-ae41-b131d8d408c0', 'Beijing Bicycle (Shiqi sui de dan che)',
        'Cras mi pede, malesuada in, imperdiet et, commodo vulputate, justo. In blandit ultrices enim. Lorem ipsum dolor sit amet, consectetuer adipiscing elit.',
        'easy', 600, 0, 16, '2023-01-11 16:47:11', 1, '2023-01-19 14:32:46');
INSERT INTO public.test (id, uuid, name, description, complexity, duration, total_attempt_number, subject_id,
                         expiration_date, max_attempt_number, created) OVERRIDING SYSTEM VALUE
VALUES (82, '43304449-c406-493e-bf44-692e81e9afc6', 'Last Godfather, The',
        'Nullam sit amet turpis elementum ligula vehicula consequat. Morbi a ipsum. Integer a nibh.', 'easy', 600, 0, 4,
        '2023-01-11 16:47:11', 1, '2023-01-19 14:32:46');
INSERT INTO public.test (id, uuid, name, description, complexity, duration, total_attempt_number, subject_id,
                         expiration_date, max_attempt_number, created) OVERRIDING SYSTEM VALUE
VALUES (139, '49aee6f9-251c-4c51-b63c-8b6a5a7f0068', 'Tachyglossus aculeatus', 'In sagittis dui vel nisl.', 'easy', 900,
        0, 9, '2023-01-11 16:47:11', 1, '2023-01-19 14:32:46');
INSERT INTO public.test (id, uuid, name, description, complexity, duration, total_attempt_number, subject_id,
                         expiration_date, max_attempt_number, created) OVERRIDING SYSTEM VALUE
VALUES (138, '282d0dab-3a45-4621-a260-b58c47eed49a', 'Phalacrocorax varius', 'Nunc nisl.', 'easy', 900, 0, 8,
        '2023-01-11 16:47:11', 1, '2023-01-19 14:32:46');
INSERT INTO public.test (id, uuid, name, description, complexity, duration, total_attempt_number, subject_id,
                         expiration_date, max_attempt_number, created) OVERRIDING SYSTEM VALUE
VALUES (157, 'dc9ae3ff-a01b-4d8e-80c1-f13eee4383bf', 'Vulpes chama', 'Proin risus.', 'easy', 900, 0, 7,
        '2023-01-11 16:47:11', 1, '2023-01-19 14:32:46');
INSERT INTO public.test (id, uuid, name, description, complexity, duration, total_attempt_number, subject_id,
                         expiration_date, max_attempt_number, created) OVERRIDING SYSTEM VALUE
VALUES (159, 'b6c4045d-5987-4090-aa26-1a33382468d2', 'Haematopus ater', 'Sed vel enim sit amet nunc viverra dapibus.',
        'easy', 900, 0, 9, '2023-01-11 16:47:11', 1, '2023-01-19 14:32:46');
INSERT INTO public.test (id, uuid, name, description, complexity, duration, total_attempt_number, subject_id,
                         expiration_date, max_attempt_number, created) OVERRIDING SYSTEM VALUE
VALUES (158, 'f8a66467-ab39-4420-9393-69588c589c92', 'Alcelaphus buselaphus cokii',
        'Quisque id justo sit amet sapien dignissim vestibulum.', 'easy', 900, 0, 8, '2023-01-11 16:47:11', 1,
        '2023-01-19 14:32:46');
INSERT INTO public.test (id, uuid, name, description, complexity, duration, total_attempt_number, subject_id,
                         expiration_date, max_attempt_number, created) OVERRIDING SYSTEM VALUE
VALUES (145, '2b751a56-777b-4430-a2b2-3059bc2b0a0b', 'Lepilemur rufescens', 'Pellentesque eget nunc.', 'easy', 900, 0,
        5, '2023-01-11 16:47:11', 1, '2023-01-19 14:32:46');
INSERT INTO public.test (id, uuid, name, description, complexity, duration, total_attempt_number, subject_id,
                         expiration_date, max_attempt_number, created) OVERRIDING SYSTEM VALUE
VALUES (144, 'ecb1497a-1da0-423f-a07c-2bbe0525061c', 'Toxostoma curvirostre', 'Nam dui.', 'easy', 900, 0, 4,
        '2023-01-11 16:47:11', 1, '2023-01-19 14:32:46');
INSERT INTO public.test (id, uuid, name, description, complexity, duration, total_attempt_number, subject_id,
                         expiration_date, max_attempt_number, created) OVERRIDING SYSTEM VALUE
VALUES (147, '818a1b46-86dd-4731-883a-79bf53f34aaa', 'Centrocercus urophasianus', 'In hac habitasse platea dictumst.',
        'easy', 900, 0, 7, '2023-01-11 16:47:11', 1, '2023-01-19 14:32:46');
INSERT INTO public.test (id, uuid, name, description, complexity, duration, total_attempt_number, subject_id,
                         expiration_date, max_attempt_number, created) OVERRIDING SYSTEM VALUE
VALUES (81, 'f169a0ba-c9c0-4fad-b60d-00ec74b066cd', 'Quiet Man, The',
        'Aenean fermentum. Donec ut mauris eget massa tempor convallis. Nulla neque libero, convallis eget, eleifend luctus, ultricies eu, nibh.',
        'easy', 600, 0, NULL, '2023-01-11 16:47:11', 1, '2023-01-19 14:32:46');
INSERT INTO public.test (id, uuid, name, description, complexity, duration, total_attempt_number, subject_id,
                         expiration_date, max_attempt_number, created) OVERRIDING SYSTEM VALUE
VALUES (90, '402d341c-c5a6-480a-a938-723e13096ad6', 'Kept Husbands',
        'Aenean fermentum. Donec ut mauris eget massa tempor convallis. Nulla neque libero, convallis eget, eleifend luctus, ultricies eu, nibh.',
        'easy', 600, 0, NULL, '2023-01-11 16:47:11', 1, '2023-01-19 14:32:46');
INSERT INTO public.test (id, uuid, name, description, complexity, duration, total_attempt_number, subject_id,
                         expiration_date, max_attempt_number, created) OVERRIDING SYSTEM VALUE
VALUES (87, 'ca45f427-f483-4550-ab32-716ad0db02cc', 'Die Fischerin',
        'Duis bibendum. Morbi non quam nec dui luctus rutrum. Nulla tellus.', 'easy', 600, 0, NULL,
        '2023-01-11 16:47:11', 1, '2023-01-19 14:32:46');
INSERT INTO public.test (id, uuid, name, description, complexity, duration, total_attempt_number, subject_id,
                         expiration_date, max_attempt_number, created) OVERRIDING SYSTEM VALUE
VALUES (136, 'b33ee77e-479a-42dd-934b-bb2d0e8c5a16', 'Psophia viridis',
        'Morbi sem mauris, laoreet ut, rhoncus aliquet, pulvinar sed, nisl.', 'easy', 900, 0, NULL,
        '2023-01-11 16:47:11', 1, '2023-01-19 14:32:46');
INSERT INTO public.test (id, uuid, name, description, complexity, duration, total_attempt_number, subject_id,
                         expiration_date, max_attempt_number, created) OVERRIDING SYSTEM VALUE
VALUES (156, '6bb055c5-a004-4e6a-a281-b9610b91f991', 'Junonia genoveua', 'Morbi ut odio.', 'easy', 900, 0, NULL,
        '2023-01-11 16:47:11', 1, '2023-01-19 14:32:46');
INSERT INTO public.test (id, uuid, name, description, complexity, duration, total_attempt_number, subject_id,
                         expiration_date, max_attempt_number, created) OVERRIDING SYSTEM VALUE
VALUES (146, 'dc0f45d8-2785-44c8-9952-cfdc2ae82ab4', 'Spermophilus lateralis', 'Quisque ut erat.', 'easy', 900, 0, NULL,
        '2023-01-11 16:47:11', 1, '2023-01-19 14:32:46');

-- Population the question table
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (1, 'daf8490e-b578-4439-892d-2af9f190c47e', 'multiple_choice', 'Aliquam erat volutpat.',
        'Nullam molestie nibh in lectus.', 1, '2023-01-19 14:31:29');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (2, '5b992f20-588b-4f96-b4c7-2adb0ca32295', 'multiple_choice', 'In hac habitasse platea dictumst.',
        'Phasellus in felis.', 2, '2023-01-19 14:31:29');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (3, 'f9592a5a-6282-42a9-a1ac-adb8bfef30a5', 'multiple_choice', 'Praesent blandit.',
        'Ut at dolor quis odio consequat varius.', 3, '2023-01-19 14:31:29');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (4, 'ab9b1e33-2228-4b60-9124-434bc9630ed6', 'multiple_choice', 'Mauris ullamcorper purus sit amet nulla.',
        'Maecenas tincidunt lacus at velit.', 4, '2023-01-19 14:31:29');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (5, '90fcfc6d-4d67-4cb7-ab29-7cc75e7a53bf', 'multiple_choice', 'Morbi non quam nec dui luctus rutrum.',
        'Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Nulla dapibus dolor vel est.',
        5, '2023-01-19 14:31:29');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (6, 'e1f85b2a-4ebe-4b41-bbfe-38f2c2ebcfdb', 'multiple_choice',
        'Vivamus metus arcu, adipiscing molestie, hendrerit at, vulputate vitae, nisl.',
        'Morbi non quam nec dui luctus rutrum.', 6, '2023-01-19 14:31:29');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (7, '4f1ee654-2aea-4ca8-b7d1-f1e7fe0687b3', 'multiple_choice', 'Integer a nibh.',
        'Nam tristique tortor eu pede.', 7, '2023-01-19 14:31:29');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (8, '5f2111dc-7422-4845-8106-0c017fd43128', 'multiple_choice', 'Nulla tempus.',
        'Pellentesque ultrices mattis odio.', 8, '2023-01-19 14:31:29');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (9, '5e3e3fff-57bb-4d6c-ba64-d0ca218cd7e8', 'multiple_choice', 'Nunc nisl.', 'In hac habitasse platea dictumst.',
        9, '2023-01-19 14:31:29');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (10, 'a26f0070-6f11-4fd4-b69d-2bd686230506', 'multiple_choice',
        'Aliquam sit amet diam in magna bibendum imperdiet.', 'Etiam faucibus cursus urna.', 10, '2023-01-19 14:31:29');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (11, 'e63ded12-60f1-4c18-b175-d1f64a53455b', 'multiple_choice',
        'Nam congue, risus semper porta volutpat, quam pede lobortis ligula, sit amet eleifend pede libero quis orci.',
        'Morbi a ipsum.', 11, '2023-01-19 14:31:29');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (12, '88305e82-99ae-43fc-959c-4213d9582c8f', 'multiple_choice', 'Praesent lectus.',
        'Donec odio justo, sollicitudin ut, suscipit a, feugiat et, eros.', 12, '2023-01-19 14:31:29');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (13, 'bf35379a-c72a-4b0b-9d5b-15a114b35239', 'multiple_choice', 'Aliquam non mauris.', 'Vivamus tortor.', 13,
        '2023-01-19 14:31:29');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (14, '36befbc7-c0c7-4b95-814b-e8f2ea4c53eb', 'multiple_choice', 'Ut at dolor quis odio consequat varius.',
        'Nulla justo.', 14, '2023-01-19 14:31:29');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (15, '2976832f-790f-4b99-a9e7-6266e57eddd6', 'multiple_choice', 'Suspendisse potenti.', 'Integer non velit.', 15,
        '2023-01-19 14:31:29');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (16, 'f2dd1f41-9b27-4729-95a0-ec543bd087ac', 'multiple_choice', 'Morbi ut odio.', 'Integer a nibh.', 16,
        '2023-01-19 14:31:29');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (17, '026f1cd8-afaa-4fbd-baa3-72d5053be1f4', 'multiple_choice', 'Duis at velit eu est congue elementum.',
        'Integer ac neque.', 17, '2023-01-19 14:31:29');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (18, '7c3f6c44-8c83-4775-a5fc-385ef1824eb3', 'multiple_choice', 'Aenean auctor gravida sem.',
        'Vivamus metus arcu, adipiscing molestie, hendrerit at, vulputate vitae, nisl.', 18, '2023-01-19 14:31:29');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (19, '07725ce9-85f7-4c77-b2b1-b802e79899a4', 'multiple_choice', 'Praesent blandit.', 'Nullam varius.', 19,
        '2023-01-19 14:31:29');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (20, '2c918f04-cc1b-4148-ae98-8642eed06c2a', 'multiple_choice', 'Mauris ullamcorper purus sit amet nulla.',
        'Nam congue, risus semper porta volutpat, quam pede lobortis ligula, sit amet eleifend pede libero quis orci.',
        20, '2023-01-19 14:31:29');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (21, 'a1a3a0fd-a324-4a70-98a0-abfa6d8f71ba', 'multiple_choice', 'In eleifend quam a odio.',
        'Pellentesque at nulla.', 21, '2023-01-19 14:31:29');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (22, '272be4f5-5808-44c0-a496-89f7e0285039', 'multiple_choice',
        'Vestibulum ac est lacinia nisi venenatis tristique.', 'In hac habitasse platea dictumst.', 22,
        '2023-01-19 14:31:29');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (23, '5f113afd-98a9-41b0-bc8b-ae4d831398d3', 'multiple_choice',
        'Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus.',
        'Vestibulum sed magna at nunc commodo placerat.', 23, '2023-01-19 14:31:29');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (24, 'e8b840d9-42a6-4db4-b7f7-6cb9d08aebe4', 'multiple_choice',
        'Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Nulla dapibus dolor vel est.',
        'Aliquam erat volutpat.', 24, '2023-01-19 14:31:29');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (25, '4370ce25-056c-41c8-9b01-bea14cb2cb1d', 'multiple_choice', 'Integer non velit.',
        'Vestibulum rutrum rutrum neque.', 25, '2023-01-19 14:31:29');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (26, '1140bc5e-5682-4416-83b9-ad94fbc56f2e', 'multiple_choice', 'Nulla tellus.',
        'Fusce congue, diam id ornare imperdiet, sapien urna pretium nisl, ut volutpat sapien arcu sed augue.', 26,
        '2023-01-19 14:31:29');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (27, '36ac756d-9c05-4be2-88d6-3c26e0689286', 'multiple_choice',
        'Nam ultrices, libero non mattis pulvinar, nulla pede ullamcorper augue, a suscipit nulla elit ac nulla.',
        'Donec semper sapien a libero.', 27, '2023-01-19 14:31:29');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (28, '168634e8-fc5c-4324-a4f3-b8fbde6e0f09', 'multiple_choice', 'Praesent blandit.',
        'Duis at velit eu est congue elementum.', 28, '2023-01-19 14:31:29');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (29, 'fab21227-da0c-4b94-8709-09cc3ffd64c3', 'multiple_choice', 'Praesent lectus.',
        'Quisque arcu libero, rutrum ac, lobortis vel, dapibus at, diam.', 29, '2023-01-19 14:31:29');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (30, '567cc796-e8b4-4315-be34-fe79cc7d6111', 'multiple_choice', 'Duis bibendum.',
        'Vivamus vestibulum sagittis sapien.', 30, '2023-01-19 14:31:29');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (31, 'edcfde9c-6768-417a-80d8-e347550aa324', 'multiple_choice', 'Nunc purus.', 'Nam tristique tortor eu pede.',
        31, '2023-01-19 14:31:29');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (32, '7c6d7df1-a7be-48f2-90b4-53b5182e5028', 'multiple_choice', 'Nam dui.',
        'Vestibulum ac est lacinia nisi venenatis tristique.', 32, '2023-01-19 14:31:29');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (33, '560764e4-4130-4ca2-b400-5ae7d7eb4464', 'multiple_choice', 'Sed sagittis.', 'In blandit ultrices enim.', 33,
        '2023-01-19 14:31:29');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (34, 'd20a64d9-3a21-4e62-82b1-bb1abf31e2b5', 'multiple_choice', 'Phasellus id sapien in sapien iaculis congue.',
        'Proin at turpis a pede posuere nonummy.', 34, '2023-01-19 14:31:29');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (35, '53d3e0c7-8546-44f0-b76a-99ca5d7c23d4', 'multiple_choice',
        'Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Mauris viverra diam vitae quam.',
        'Aliquam quis turpis eget elit sodales scelerisque.', 35, '2023-01-19 14:31:29');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (36, 'e7684503-e8dd-482a-942f-5e88e13bef76', 'multiple_choice', 'Nam tristique tortor eu pede.',
        'Curabitur gravida nisi at nibh.', 36, '2023-01-19 14:31:29');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (37, 'e87dc980-0709-4f50-8a61-6c72cb9cac77', 'multiple_choice', 'Duis bibendum.', 'Curabitur convallis.', 37,
        '2023-01-19 14:31:29');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (38, '743bca94-37ee-4d9c-b93d-3cb61c83766c', 'multiple_choice', 'Nam tristique tortor eu pede.',
        'Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Mauris viverra diam vitae quam.',
        38, '2023-01-19 14:31:29');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (39, '5a89eb1a-c394-4df5-95af-6868872b0a58', 'multiple_choice',
        'Lorem ipsum dolor sit amet, consectetuer adipiscing elit.', 'Vestibulum sed magna at nunc commodo placerat.',
        39, '2023-01-19 14:31:29');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (40, '728d1262-509f-4c32-8643-4238ad05a1af', 'multiple_choice', 'In hac habitasse platea dictumst.',
        'Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus.', 40,
        '2023-01-19 14:31:29');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (41, 'bb9a1e2b-3319-4f42-a10b-f9cc2dc535e1', 'multiple_choice', 'Ut tellus.', 'Duis ac nibh.', 41,
        '2023-01-19 14:31:29');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (42, '41fc9954-bda9-4baf-87d1-36447de04408', 'multiple_choice', 'In quis justo.',
        'Cras pellentesque volutpat dui.', 42, '2023-01-19 14:31:29');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (43, 'f952a995-1571-4216-afc4-3e5cbe0ae229', 'multiple_choice', 'Maecenas ut massa quis augue luctus tincidunt.',
        'Integer non velit.', 43, '2023-01-19 14:31:29');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (44, '4bd2648b-89d0-4117-903a-8163dc2d01b6', 'multiple_choice', 'Nulla nisl.', 'Nunc nisl.', 44,
        '2023-01-19 14:31:29');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (45, '6d46af4e-2ea8-40e9-8fc7-dee5dcfe6107', 'multiple_choice', 'Aenean lectus.',
        'Pellentesque ultrices mattis odio.', 45, '2023-01-19 14:31:29');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (46, '1486b1f3-b81d-446f-b7cd-6ad45ca6ad07', 'multiple_choice', 'Morbi non quam nec dui luctus rutrum.',
        'Sed ante.', 46, '2023-01-19 14:31:29');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (47, 'a8182048-60ef-406b-9974-9cc1cc3cd19f', 'multiple_choice',
        'Nam congue, risus semper porta volutpat, quam pede lobortis ligula, sit amet eleifend pede libero quis orci.',
        'Fusce consequat.', 47, '2023-01-19 14:31:29');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (48, 'c7e6dbe4-1408-4c66-917b-0b7a5aa161cd', 'multiple_choice', 'Proin risus.',
        'Phasellus id sapien in sapien iaculis congue.', 48, '2023-01-19 14:31:29');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (49, 'aae76587-79c2-4002-a8df-5c7d28c6fe60', 'multiple_choice', 'Vivamus vestibulum sagittis sapien.',
        'Pellentesque eget nunc.', 49, '2023-01-19 14:31:29');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (50, '1ad4cd61-438b-400a-9209-ad7dfcc9620e', 'multiple_choice', 'Suspendisse ornare consequat lectus.',
        'Proin leo odio, porttitor id, consequat in, consequat ut, nulla.', 50, '2023-01-19 14:31:29');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (51, 'ebc98757-96e2-4ae3-8a07-ba4805fb0fa6', 'single_choice', 'Nulla nisl.',
        'Donec diam neque, vestibulum eget, vulputate ut, ultrices vel, augue.', 51, '2023-01-19 14:31:29');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (52, '52d35539-e504-4d61-9fb3-eeeae6866146', 'single_choice', 'Morbi ut odio.',
        'In hac habitasse platea dictumst.', 52, '2023-01-19 14:31:29');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (53, '0fda7dbe-14a3-4f8f-ad8d-1638d08681e1', 'single_choice', 'Ut at dolor quis odio consequat varius.',
        'Morbi odio odio, elementum eu, interdum eu, tincidunt in, leo.', 53, '2023-01-19 14:31:29');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (54, 'd366ef3a-6c55-4b0e-8ac0-123152320926', 'single_choice', 'Nulla ac enim.',
        'Fusce lacus purus, aliquet at, feugiat non, pretium quis, lectus.', 54, '2023-01-19 14:31:29');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (55, '79f241b7-9c93-4f43-a6b0-dc1cd7dda258', 'single_choice', 'In hac habitasse platea dictumst.',
        'Etiam justo.', 55, '2023-01-19 14:31:29');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (56, 'b31ff59e-0f42-4299-968d-bc04b0f6db02', 'single_choice', 'Aenean sit amet justo.', 'Nulla justo.', 56,
        '2023-01-19 14:31:29');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (57, '94f84fe7-48eb-4544-8c19-a85768841f1c', 'single_choice',
        'Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Duis faucibus accumsan odio.',
        'Mauris sit amet eros.', 57, '2023-01-19 14:31:29');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (58, '067be8c7-a9c9-4e9c-aeb4-6a48cef63f5a', 'single_choice',
        'Donec diam neque, vestibulum eget, vulputate ut, ultrices vel, augue.', 'Maecenas pulvinar lobortis est.', 58,
        '2023-01-19 14:31:29');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (59, '01d1ca5c-6086-4326-b8a8-0be90674e557', 'single_choice', 'Sed ante.',
        'Nulla neque libero, convallis eget, eleifend luctus, ultricies eu, nibh.', 59, '2023-01-19 14:31:29');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (60, '6c045ac6-e32d-4d53-963e-8aa01d8af280', 'single_choice', 'Aliquam erat volutpat.',
        'Phasellus sit amet erat.', 60, '2023-01-19 14:31:29');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (61, '94a67226-93c3-4f91-a81b-f25f76a5f0c0', 'single_choice', 'Duis ac nibh.', 'Maecenas pulvinar lobortis est.',
        61, '2023-01-19 14:31:29');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (62, '255244a1-41a5-4ed3-94ed-d779a4afd085', 'single_choice', 'Nunc rhoncus dui vel sem.',
        'Nam congue, risus semper porta volutpat, quam pede lobortis ligula, sit amet eleifend pede libero quis orci.',
        62, '2023-01-19 14:31:29');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (63, 'b72ddd96-6a0c-4692-9a27-ceeb241de1d1', 'single_choice', 'Nullam porttitor lacus at turpis.',
        'Maecenas tristique, est et tempus semper, est quam pharetra magna, ac consequat metus sapien ut nunc.', 63,
        '2023-01-19 14:31:29');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (64, 'c105a802-8d5d-454b-a0b6-dab8e4ef75f9', 'single_choice',
        'Donec odio justo, sollicitudin ut, suscipit a, feugiat et, eros.', 'Phasellus sit amet erat.', 64,
        '2023-01-19 14:31:29');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (65, 'e7b2df53-2a24-41a1-b162-e203fc948296', 'single_choice', 'Mauris ullamcorper purus sit amet nulla.',
        'Cras mi pede, malesuada in, imperdiet et, commodo vulputate, justo.', 65, '2023-01-19 14:31:29');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (66, 'c605f428-e17f-401e-971b-c0190fbb0654', 'single_choice', 'Nulla tempus.',
        'Curabitur in libero ut massa volutpat convallis.', 66, '2023-01-19 14:31:29');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (67, '2e62f501-fa7d-46ea-9116-4343237b5d10', 'single_choice', 'Cras pellentesque volutpat dui.',
        'Morbi non quam nec dui luctus rutrum.', 67, '2023-01-19 14:31:29');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (68, '7cf1034c-c533-44bb-abf8-6769d1b1cedd', 'single_choice', 'Nunc purus.',
        'Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Duis faucibus accumsan odio.',
        68, '2023-01-19 14:31:29');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (69, 'd8f82496-f759-4eae-a529-e77a6f04c225', 'single_choice', 'Phasellus sit amet erat.', 'Sed ante.', 69,
        '2023-01-19 14:31:29');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (70, 'bf27eb9e-88e4-4dd7-85ae-99fdd250e477', 'single_choice', 'Morbi vel lectus in quam fringilla rhoncus.',
        'Suspendisse accumsan tortor quis turpis.', 70, '2023-01-19 14:31:29');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (71, 'dddb9bb4-308e-4a3a-98fd-9df9a3e9feac', 'single_choice', 'Etiam pretium iaculis justo.',
        'Suspendisse ornare consequat lectus.', 71, '2023-01-19 14:31:29');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (72, '41f98808-6ddb-4b85-a9de-efa8cc81fa4c', 'single_choice', 'Morbi non lectus.',
        'Nulla ut erat id mauris vulputate elementum.', 72, '2023-01-19 14:31:29');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (73, '886d9f8d-bd03-4e96-8b9a-86f922ac41ad', 'single_choice',
        'Quisque arcu libero, rutrum ac, lobortis vel, dapibus at, diam.',
        'Maecenas leo odio, condimentum id, luctus nec, molestie sed, justo.', 73, '2023-01-19 14:31:29');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (74, '3914112a-f920-44b0-a107-b7afad6dedc3', 'single_choice', 'In congue.',
        'Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Donec pharetra, magna vestibulum aliquet ultrices, erat tortor sollicitudin mi, sit amet lobortis sapien sapien non mi.',
        74, '2023-01-19 14:31:29');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (75, 'b2015c4f-a3b7-4dd1-854f-59a16abb33de', 'single_choice', 'In sagittis dui vel nisl.', 'Phasellus in felis.',
        75, '2023-01-19 14:31:29');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (76, '16979066-f712-4126-b8d9-ea2067222ca9', 'single_choice', 'Curabitur gravida nisi at nibh.', 'Nulla justo.',
        76, '2023-01-19 14:31:29');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (77, 'ed35a8dc-788f-4cb7-9a60-53a840f967d5', 'single_choice', 'Ut at dolor quis odio consequat varius.',
        'Nullam orci pede, venenatis non, sodales sed, tincidunt eu, felis.', 77, '2023-01-19 14:31:29');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (78, 'bd96bdfe-9da0-4f24-b3b8-4bc02a6fefb0', 'single_choice', 'Morbi vel lectus in quam fringilla rhoncus.',
        'Morbi non quam nec dui luctus rutrum.', 78, '2023-01-19 14:31:29');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (79, '184b75a7-4f83-4a0b-9cde-daca5c11315b', 'single_choice', 'Aenean fermentum.',
        'Maecenas ut massa quis augue luctus tincidunt.', 79, '2023-01-19 14:31:29');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (80, '4699fffd-6029-4590-b60e-9ddf88c0d650', 'single_choice', 'In hac habitasse platea dictumst.',
        'Aenean lectus.', 80, '2023-01-19 14:31:29');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (81, '4b382bac-0aaf-4ce7-8c48-0feeb5aa8bf4', 'single_choice', 'Duis at velit eu est congue elementum.',
        'Vivamus metus arcu, adipiscing molestie, hendrerit at, vulputate vitae, nisl.', 81, '2023-01-19 14:31:29');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (82, '74cca763-843a-4b96-9fea-5d562b01e276', 'single_choice', 'Praesent blandit.',
        'Fusce lacus purus, aliquet at, feugiat non, pretium quis, lectus.', 82, '2023-01-19 14:31:29');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (83, '90abb7be-39bb-42f4-ba3a-5d1a9cffa4cf', 'single_choice', 'Praesent blandit.',
        'Curabitur gravida nisi at nibh.', 83, '2023-01-19 14:31:29');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (84, 'aca264a6-0cf8-4433-8092-1c97c3891bc2', 'single_choice', 'Phasellus in felis.',
        'Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Mauris viverra diam vitae quam.',
        84, '2023-01-19 14:31:29');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (85, '5580a08f-ecc5-4707-b054-73b2d232a958', 'single_choice', 'In hac habitasse platea dictumst.',
        'Vivamus vel nulla eget eros elementum pellentesque.', 85, '2023-01-19 14:31:29');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (86, '7babbafa-7acb-4789-af46-b10d08bb2cac', 'single_choice',
        'Nam congue, risus semper porta volutpat, quam pede lobortis ligula, sit amet eleifend pede libero quis orci.',
        'Nullam orci pede, venenatis non, sodales sed, tincidunt eu, felis.', 86, '2023-01-19 14:31:29');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (88, '043b17e9-03ed-498d-bd72-a67035a24fe1', 'single_choice', 'Pellentesque eget nunc.',
        'Cras non velit nec nisi vulputate nonummy.', 88, '2023-01-19 14:31:29');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (90, '253b134c-0896-4c0c-b694-0d09fafa08e8', 'single_choice', 'Cras pellentesque volutpat dui.',
        'Praesent lectus.', 90, '2023-01-19 14:31:29');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (91, '84302c08-c1f1-483c-a785-437a7f31a80b', 'single_choice', 'Sed ante.', 'Vivamus tortor.', 91,
        '2023-01-19 14:31:29');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (92, '0896090d-6db4-4e6d-85a7-e2ffe20be99e', 'single_choice', 'Pellentesque viverra pede ac diam.',
        'Nunc rhoncus dui vel sem.', 92, '2023-01-19 14:31:29');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (93, '74c77841-d399-45c7-b99c-3078512fbd64', 'single_choice', 'Integer ac leo.',
        'Morbi vestibulum, velit id pretium iaculis, diam erat fermentum justo, nec condimentum neque sapien placerat ante.',
        93, '2023-01-19 14:31:29');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (94, 'd714bfed-50b8-4480-8d41-6e3c6b79fee8', 'single_choice', 'Donec vitae nisi.',
        'Morbi porttitor lorem id ligula.', 94, '2023-01-19 14:31:29');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (95, '6a8d9025-0595-4fb0-b2b2-80e020200551', 'single_choice', 'Pellentesque at nulla.', 'Nam dui.', 95,
        '2023-01-19 14:31:29');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (96, 'cd79671d-fa25-4557-9398-9920db255146', 'single_choice', 'Nam dui.', 'Vivamus vestibulum sagittis sapien.',
        96, '2023-01-19 14:31:29');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (97, '066c7889-0449-434f-bd18-5792439d8881', 'single_choice', 'Donec dapibus.',
        'Lorem ipsum dolor sit amet, consectetuer adipiscing elit.', 97, '2023-01-19 14:31:29');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (99, '69b089de-2050-4447-9644-e93a05b5062b', 'single_choice', 'Nam tristique tortor eu pede.',
        'Etiam pretium iaculis justo.', 99, '2023-01-19 14:31:29');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (100, 'ca0d9177-bd4d-42e3-aa15-1c1153a87a6f', 'single_choice', 'Donec vitae nisi.',
        'Integer aliquet, massa id lobortis convallis, tortor risus dapibus augue, vel accumsan tellus nisi eu orci.',
        100, '2023-01-19 14:31:29');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (101, '8688432e-6d23-4feb-baf3-6947569164b4', 'true_false', 'Aliquam sit amet diam in magna bibendum imperdiet.',
        'Vivamus vel nulla eget eros elementum pellentesque.', 101, '2023-01-19 14:31:29');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (102, '2011fd22-5275-4efc-a0c9-5785508f7c08', 'true_false', 'Donec ut mauris eget massa tempor convallis.',
        'Nam nulla.', 102, '2023-01-19 14:31:29');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (103, 'deb3e6d3-9c3f-469b-9606-f78387512822', 'true_false', 'Duis ac nibh.', 'Pellentesque eget nunc.', 103,
        '2023-01-19 14:31:29');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (104, '672fa86c-c23e-45be-988f-cc946072966a', 'true_false', 'Suspendisse ornare consequat lectus.',
        'Mauris sit amet eros.', 104, '2023-01-19 14:31:29');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (105, '979fe5ff-7a16-4fc6-b131-12983bc2bfad', 'true_false', 'Vestibulum rutrum rutrum neque.',
        'Curabitur convallis.', 105, '2023-01-19 14:31:29');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (106, '1a1820a5-4b30-4f02-a6fc-fa7bf3df216e', 'true_false', 'Pellentesque viverra pede ac diam.',
        'In blandit ultrices enim.', 106, '2023-01-19 14:31:29');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (107, '509cf630-a35d-40e6-ac17-bce83b8a45b2', 'true_false',
        'Mauris enim leo, rhoncus sed, vestibulum sit amet, cursus id, turpis.',
        'Morbi odio odio, elementum eu, interdum eu, tincidunt in, leo.', 107, '2023-01-19 14:31:29');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (108, '5ba9f899-c224-461c-9901-8166029bd475', 'true_false', 'Integer ac neque.',
        'Nullam porttitor lacus at turpis.', 108, '2023-01-19 14:31:29');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (109, 'b24f3144-4e35-45f5-92a1-e1744aa55fca', 'true_false', 'Duis ac nibh.', 'Duis mattis egestas metus.', 109,
        '2023-01-19 14:31:29');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (110, '50eb97bd-ff07-43bc-97b0-6d21d824079a', 'true_false', 'Duis ac nibh.',
        'Duis consequat dui nec nisi volutpat eleifend.', 110, '2023-01-19 14:31:29');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (111, '8df7b4d2-19ad-40f2-8b9f-91d5035e82e0', 'true_false', 'Vivamus in felis eu sapien cursus vestibulum.',
        'Fusce posuere felis sed lacus.', 111, '2023-01-19 14:31:29');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (112, '00f0157f-706a-41fa-9ba4-f5cba341a36d', 'true_false', 'Nunc rhoncus dui vel sem.',
        'Vestibulum rutrum rutrum neque.', 112, '2023-01-19 14:31:29');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (113, 'ce49ce15-66b0-4c1f-bcb6-2e4b1b7cff1f', 'true_false',
        'Morbi odio odio, elementum eu, interdum eu, tincidunt in, leo.',
        'Integer aliquet, massa id lobortis convallis, tortor risus dapibus augue, vel accumsan tellus nisi eu orci.',
        113, '2023-01-19 14:31:29');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (114, '765652d0-e998-4605-9555-ca9458560b97', 'true_false', 'Phasellus id sapien in sapien iaculis congue.',
        'Nam nulla.', 114, '2023-01-19 14:31:29');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (116, '5d27db2a-ba4a-4494-a353-15b81567eef7', 'true_false',
        'Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Nulla dapibus dolor vel est.',
        'Pellentesque ultrices mattis odio.', 116, '2023-01-19 14:31:29');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (117, '08a85198-bc65-4523-9e8f-5bfa8ec54035', 'true_false', 'Aliquam non mauris.', 'Suspendisse potenti.', 117,
        '2023-01-19 14:31:29');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (118, 'a336aec0-65a7-4c1b-91b0-2a0de72be26c', 'true_false',
        'Duis bibendum, felis sed interdum venenatis, turpis enim blandit mi, in porttitor pede justo eu massa.',
        'Cras non velit nec nisi vulputate nonummy.', 118, '2023-01-19 14:31:29');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (119, '70370990-68a9-4099-a2b4-92ed60a498c9', 'true_false',
        'Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus.',
        'Fusce lacus purus, aliquet at, feugiat non, pretium quis, lectus.', 119, '2023-01-19 14:31:29');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (120, '2afd2221-af1a-4021-ad0f-aea14dfffcba', 'true_false', 'In sagittis dui vel nisl.',
        'Integer tincidunt ante vel ipsum.', 120, '2023-01-19 14:31:29');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (121, '58399242-0b2d-4bae-8939-37cb75ccd7bf', 'true_false', 'Sed accumsan felis.', 'Praesent lectus.', 121,
        '2023-01-19 14:31:29');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (122, '919c1a39-5d22-48ec-9454-e07ef55be699', 'true_false', 'Phasellus sit amet erat.',
        'Vestibulum rutrum rutrum neque.', 122, '2023-01-19 14:31:29');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (123, 'ee2b5aa7-5c4e-4363-b5b4-509f7c669554', 'true_false',
        'Nam ultrices, libero non mattis pulvinar, nulla pede ullamcorper augue, a suscipit nulla elit ac nulla.',
        'Nulla nisl.', 123, '2023-01-19 14:31:29');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (124, 'a5e7414d-523e-4a72-965c-1e05948fefdf', 'true_false', 'Nam dui.', 'Aenean auctor gravida sem.', 124,
        '2023-01-19 14:31:29');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (125, '339723e5-9cdc-4f71-b6fd-f5fa207d87a7', 'true_false',
        'Morbi odio odio, elementum eu, interdum eu, tincidunt in, leo.', 'In hac habitasse platea dictumst.', 125,
        '2023-01-19 14:31:29');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (126, '8e6c649a-9119-4647-808d-740a03066a65', 'true_false', 'Suspendisse potenti.',
        'In hac habitasse platea dictumst.', 126, '2023-01-19 14:31:29');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (127, 'f2ed25e5-9a05-4276-9562-833da8079604', 'true_false', 'Donec ut dolor.', 'Duis mattis egestas metus.', 127,
        '2023-01-19 14:31:29');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (129, 'a3e9d05c-a494-4077-bd15-f3ef376e0a74', 'true_false',
        'Vivamus metus arcu, adipiscing molestie, hendrerit at, vulputate vitae, nisl.',
        'Nam ultrices, libero non mattis pulvinar, nulla pede ullamcorper augue, a suscipit nulla elit ac nulla.', 129,
        '2023-01-19 14:31:29');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (130, '658ca531-909a-498b-bc41-0d183a2ea6d4', 'true_false', 'Suspendisse potenti.',
        'Vestibulum ac est lacinia nisi venenatis tristique.', 130, '2023-01-19 14:31:29');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (131, '062fd272-d353-4dd7-8c83-becb1483436b', 'numerical',
        'Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Duis faucibus accumsan odio.',
        'Nullam porttitor lacus at turpis.', 131, '2023-01-19 14:31:29');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (132, '7161abe0-f942-4b7c-bf91-c132db75b91b', 'numerical', 'Praesent id massa id nisl venenatis lacinia.',
        'Morbi odio odio, elementum eu, interdum eu, tincidunt in, leo.', 132, '2023-01-19 14:31:29');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (133, '849b6684-8594-4630-b710-d9ccde77f024', 'numerical',
        'Quisque erat eros, viverra eget, congue eget, semper rutrum, nulla.', 'In hac habitasse platea dictumst.', 133,
        '2023-01-19 14:31:29');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (134, '7bca6ada-4c72-4620-a864-ff23fc167db7', 'numerical',
        'Lorem ipsum dolor sit amet, consectetuer adipiscing elit.', 'Vestibulum sed magna at nunc commodo placerat.',
        134, '2023-01-19 14:31:29');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (135, 'e069f7a3-0e98-4b1a-9ea3-93ac7dde6c6b', 'numerical', 'Sed sagittis.', 'Nulla tempus.', 135,
        '2023-01-19 14:31:29');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (136, 'b6f28805-b257-4ccf-8be1-c13820c77136', 'numerical',
        'In tempor, turpis nec euismod scelerisque, quam turpis adipiscing lorem, vitae mattis nibh ligula nec sem.',
        'Integer ac neque.', 136, '2023-01-19 14:31:29');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (137, 'd6237167-51cc-4620-9f06-4c16c869922f', 'numerical', 'Mauris ullamcorper purus sit amet nulla.',
        'Suspendisse potenti.', 137, '2023-01-19 14:31:29');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (138, '4cc8155c-5c7a-45ba-b69f-68d3d6eebab2', 'numerical', 'Curabitur convallis.', 'Suspendisse potenti.', 138,
        '2023-01-19 14:31:29');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (139, '6ab18a6e-4649-4b3c-af37-ad65bf8c69b3', 'numerical', 'Morbi non lectus.',
        'Donec posuere metus vitae ipsum.', 139, '2023-01-19 14:31:29');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (140, '5d183ccb-ce17-42d2-928f-d68931316e34', 'numerical', 'Curabitur gravida nisi at nibh.',
        'Nulla mollis molestie lorem.', 140, '2023-01-19 14:31:29');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (141, '5aa0cc63-0f9b-442e-9f49-f159a2ef9665', 'numerical', 'Nunc rhoncus dui vel sem.',
        'Maecenas ut massa quis augue luctus tincidunt.', 141, '2023-01-19 14:31:29');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (142, 'ee54acfa-a7ef-435e-9ecf-d176997f4236', 'numerical', 'Integer ac leo.',
        'Maecenas ut massa quis augue luctus tincidunt.', 142, '2023-01-19 14:31:29');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (143, '0a9d750d-cb18-4efa-9fdc-976d19294d96', 'numerical', 'Nullam molestie nibh in lectus.',
        'In hac habitasse platea dictumst.', 143, '2023-01-19 14:31:29');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (144, '598dbe0f-256e-4083-be85-9493bbad57da', 'numerical', 'Duis bibendum.',
        'Maecenas leo odio, condimentum id, luctus nec, molestie sed, justo.', 144, '2023-01-19 14:31:29');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (145, 'c29862ae-dccb-4bb2-9e7b-1e619de41389', 'numerical',
        'Vivamus metus arcu, adipiscing molestie, hendrerit at, vulputate vitae, nisl.',
        'In hac habitasse platea dictumst.', 145, '2023-01-19 14:31:29');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (146, '8bd2db0f-24a2-4f52-93e3-563a41fce289', 'numerical', 'Praesent blandit.', 'Fusce consequat.', 146,
        '2023-01-19 14:31:29');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (147, '486b9d3d-448f-4678-baa5-d2625f61ba34', 'numerical', 'Morbi quis tortor id nulla ultrices aliquet.',
        'Duis mattis egestas metus.', 147, '2023-01-19 14:31:29');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (148, '220d741f-d92a-463d-afb3-9ffbeed3a886', 'numerical',
        'Morbi sem mauris, laoreet ut, rhoncus aliquet, pulvinar sed, nisl.', 'Integer non velit.', 148,
        '2023-01-19 14:31:29');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (149, 'a1692d0a-6f43-46a4-93e8-7b5a9b272165', 'numerical', 'In sagittis dui vel nisl.', 'Morbi ut odio.', 149,
        '2023-01-19 14:31:29');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (150, '94485791-90cf-4bd4-9c47-27031d5c5966', 'numerical',
        'Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Mauris viverra diam vitae quam.',
        'Donec ut mauris eget massa tempor convallis.', 150, '2023-01-19 14:31:29');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (151, '9bf4a743-74d5-46a8-b48a-57b77378edfc', 'numerical', 'Curabitur convallis.',
        'Maecenas ut massa quis augue luctus tincidunt.', 151, '2023-01-19 14:31:29');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (152, '8e79c6f8-e935-4b1e-8e5d-57f566b7e617', 'numerical', 'Pellentesque viverra pede ac diam.',
        'Suspendisse potenti.', 152, '2023-01-19 14:31:29');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (153, '9abb3b1c-c1b9-4621-814a-19a87cf0a5de', 'numerical', 'Quisque ut erat.', 'In sagittis dui vel nisl.', 153,
        '2023-01-19 14:31:29');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (154, 'b02e058c-301f-4c7c-80f6-d77e5ede1b16', 'numerical',
        'Quisque id justo sit amet sapien dignissim vestibulum.', 'Nunc purus.', 154, '2023-01-19 14:31:29');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (155, 'e55a08b6-f048-4766-a3c9-cb688c958f4a', 'numerical', 'Nam nulla.',
        'Integer pede justo, lacinia eget, tincidunt eget, tempus vel, pede.', 155, '2023-01-19 14:31:29');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (219, '4646df45-81fb-4204-b8ab-aee452291b4b', 'multiple_choice', 'tyu', 'rtyurty', 201, '2023-01-27 21:27:48');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (128, '8f1b6297-b463-4e6c-a890-11f318473d33', 'true_false', 'Duis ac nibh.', 'In hac habitasse platea dictumst.',
        155, '2023-01-19 14:31:29');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (220, '73c56890-8140-4bf4-90a3-a334eb5acd12', 'text', 'RRRRRRRRRRR', 'rsesfs', 203, '2023-01-30 15:56:02');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (156, '9e9abf44-e46f-452d-85b8-2ac07414d4e7', 'numerical', 'Donec posuere metus vitae ipsum.',
        'Morbi vestibulum, velit id pretium iaculis, diam erat fermentum justo, nec condimentum neque sapien placerat ante.',
        156, '2023-01-19 14:31:29');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (157, '39401300-3cfc-4f07-bfdc-457cf88c4fda', 'numerical', 'Ut tellus.',
        'Nullam sit amet turpis elementum ligula vehicula consequat.', 157, '2023-01-19 14:31:29');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (158, '12c5e865-f651-439f-83fd-f7958241f090', 'numerical', 'Donec posuere metus vitae ipsum.', 'In congue.', 158,
        '2023-01-19 14:31:29');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (159, '54e1ee9b-350f-477b-9162-3b14995e1a86', 'numerical',
        'Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Mauris viverra diam vitae quam.',
        'Nam nulla.', 159, '2023-01-19 14:31:29');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (160, '9582b311-6913-403f-8b58-be45113b67e1', 'numerical',
        'Morbi vestibulum, velit id pretium iaculis, diam erat fermentum justo, nec condimentum neque sapien placerat ante.',
        'Maecenas tristique, est et tempus semper, est quam pharetra magna, ac consequat metus sapien ut nunc.', 160,
        '2023-01-19 14:31:29');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (161, '7b95c115-96c6-4681-8fd6-07192b6dd309', 'text',
        'Donec odio justo, sollicitudin ut, suscipit a, feugiat et, eros.',
        'Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Mauris viverra diam vitae quam.',
        161, '2023-01-19 14:31:29');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (162, '571b8552-b793-43fa-8545-1d8bce620841', 'text',
        'Nam ultrices, libero non mattis pulvinar, nulla pede ullamcorper augue, a suscipit nulla elit ac nulla.',
        'Quisque erat eros, viverra eget, congue eget, semper rutrum, nulla.', 162, '2023-01-19 14:31:29');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (163, '8b280422-4154-4ae7-83c8-afc92e53be62', 'text',
        'In est risus, auctor sed, tristique in, tempus sit amet, sem.',
        'Nam ultrices, libero non mattis pulvinar, nulla pede ullamcorper augue, a suscipit nulla elit ac nulla.', 163,
        '2023-01-19 14:31:29');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (164, '56e1f043-536a-425c-9368-a69a7cb8c422', 'text', 'Maecenas pulvinar lobortis est.',
        'Quisque porta volutpat erat.', 164, '2023-01-19 14:31:29');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (165, 'be8d14d7-b5d3-4928-bc8a-7afa9bdc1a64', 'text', 'Vivamus in felis eu sapien cursus vestibulum.',
        'Integer pede justo, lacinia eget, tincidunt eget, tempus vel, pede.', 165, '2023-01-19 14:31:29');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (166, 'c21092b7-bdcf-40cc-8dbe-99e486c94dfd', 'text', 'Nulla ut erat id mauris vulputate elementum.',
        'Vestibulum ac est lacinia nisi venenatis tristique.', 166, '2023-01-19 14:31:29');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (168, 'fac824fe-e9fc-4c43-9f01-39df464768cd', 'text',
        'In est risus, auctor sed, tristique in, tempus sit amet, sem.', 'Cras non velit nec nisi vulputate nonummy.',
        168, '2023-01-19 14:31:29');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (169, 'a68cd88c-1904-4ff6-b0a4-d5c02387368b', 'text', 'Aliquam sit amet diam in magna bibendum imperdiet.',
        'Aenean sit amet justo.', 169, '2023-01-19 14:31:29');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (170, '7778b57e-72dc-4c71-adfe-48bd1646da6f', 'text', 'Vivamus tortor.',
        'Donec odio justo, sollicitudin ut, suscipit a, feugiat et, eros.', 170, '2023-01-19 14:31:29');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (171, '7b9fe5da-fc11-4051-97a5-5c6339fa2b3c', 'text', 'Donec ut dolor.',
        'Ut at dolor quis odio consequat varius.', 171, '2023-01-19 14:31:29');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (172, 'e14c078b-4239-45ff-91e4-fa0d1bb7006a', 'text', 'Duis ac nibh.', 'Vivamus tortor.', 172,
        '2023-01-19 14:31:29');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (173, 'bf1ae171-bbb5-4fff-b19b-2c420814fc4b', 'text',
        'Vivamus metus arcu, adipiscing molestie, hendrerit at, vulputate vitae, nisl.', 'Nunc rhoncus dui vel sem.',
        173, '2023-01-19 14:31:29');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (174, '0ff80559-df6b-4926-a43a-0e474fdada42', 'text', 'Praesent id massa id nisl venenatis lacinia.',
        'Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Duis faucibus accumsan odio.',
        174, '2023-01-19 14:31:29');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (175, '17b8f871-c017-49cf-ad6d-dc36fba03e8e', 'text', 'Vestibulum ac est lacinia nisi venenatis tristique.',
        'Donec odio justo, sollicitudin ut, suscipit a, feugiat et, eros.', 175, '2023-01-19 14:31:29');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (176, '949d367e-454f-4e78-a998-5ab9db6f57aa', 'text',
        'Proin leo odio, porttitor id, consequat in, consequat ut, nulla.', 'Curabitur convallis.', 176,
        '2023-01-19 14:31:29');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (177, '96c926d8-b46f-47ef-9a52-9533eb62c8ce', 'text',
        'Morbi vestibulum, velit id pretium iaculis, diam erat fermentum justo, nec condimentum neque sapien placerat ante.',
        'Donec dapibus.', 177, '2023-01-19 14:31:29');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (178, 'f7be2a06-2426-474b-91dd-d83320ed4d4e', 'text', 'Suspendisse potenti.', 'Curabitur convallis.', 178,
        '2023-01-19 14:31:29');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (179, '26c6bb8e-9b3c-4a6c-a072-c1351b6b90c3', 'text', 'Quisque id justo sit amet sapien dignissim vestibulum.',
        'Vestibulum rutrum rutrum neque.', 179, '2023-01-19 14:31:29');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (180, '479df0d4-f74d-45a9-b516-3c9b6255b9b6', 'text', 'Etiam vel augue.',
        'Nullam sit amet turpis elementum ligula vehicula consequat.', 180, '2023-01-19 14:31:29');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (181, '3c97cb20-d7f1-4dda-b29f-94de84775a5d', 'text', 'Proin risus.',
        'Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus.', 181,
        '2023-01-19 14:31:29');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (182, 'a6221f92-ad78-48e0-9a2a-58b06b77f6f6', 'text',
        'Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus.',
        'Fusce lacus purus, aliquet at, feugiat non, pretium quis, lectus.', 182, '2023-01-19 14:31:29');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (183, '263be256-080f-4865-a9fa-d90bb0c1e84f', 'text',
        'Nam ultrices, libero non mattis pulvinar, nulla pede ullamcorper augue, a suscipit nulla elit ac nulla.',
        'Suspendisse potenti.', 183, '2023-01-19 14:31:29');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (184, '7d079d67-b2dc-48cc-9d26-4df61e83cd4f', 'text', 'Proin at turpis a pede posuere nonummy.',
        'Vivamus tortor.', 184, '2023-01-19 14:31:29');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (185, 'e2141790-9f15-4570-a569-7da0b7d01384', 'text',
        'In tempor, turpis nec euismod scelerisque, quam turpis adipiscing lorem, vitae mattis nibh ligula nec sem.',
        'Nam nulla.', 185, '2023-01-19 14:31:29');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (187, 'b512b013-23f5-430b-a249-f8601e1c90cf', 'text', 'In eleifend quam a odio.',
        'Nullam sit amet turpis elementum ligula vehicula consequat.', 187, '2023-01-19 14:31:29');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (188, '7ad8b448-0c81-408d-bc8e-ee1ae2a9bec1', 'text', 'Nunc purus.', 'In hac habitasse platea dictumst.', 188,
        '2023-01-19 14:31:29');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (189, 'abb522cd-6629-43ba-b190-3c97e5ce6791', 'text',
        'In est risus, auctor sed, tristique in, tempus sit amet, sem.', 'Proin risus.', 189, '2023-01-19 14:31:29');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (190, 'a252f88e-270c-4291-a22f-a5201f809a81', 'text',
        'Nam congue, risus semper porta volutpat, quam pede lobortis ligula, sit amet eleifend pede libero quis orci.',
        'Duis ac nibh.', 190, '2023-01-19 14:31:29');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (203, 'fa02fbd1-c5f4-4e45-9648-e6ba27c7a70d', 'single_choice', 'fdghjdfh', 'dfghfd', 166, '2023-01-19 14:31:29');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (194, '71c87a44-64a8-4f62-b24b-bdacb5e3b3f1', 'text', '111111111111', '111111111111', 198,
        '2023-01-19 14:31:29');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (195, '4530d861-ab2a-41ae-9643-bf72c30f7231', 'text', '2222222222222222', '2222222222222222', 196,
        '2023-01-19 14:31:29');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (196, 'd6295988-fd7f-4f36-9819-196de3b14907', 'text', 'dsrteters', 'ertertr', 197, '2023-01-19 14:31:29');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (197, '8258c023-bdf8-4135-a190-907410f046de', 'numerical', 'tryerty', 'rtyertdy', 88, '2023-01-19 14:31:29');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (204, '42c7194f-3e89-4d6c-91f8-b9e3692a3d43', 'single_choice', 'tyur', 'rtyu', 164, '2023-01-19 14:31:29');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (198, 'b67e558f-3bda-422a-9882-b7fe2cc601ae', 'single_choice', 'Questttrr', 'Questttrr', 198,
        '2023-01-19 14:31:29');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (199, '9f4cfab4-8afd-4de6-8563-059018116b1c', 'single_choice', 'Questttrr', 'Questttrr', 198,
        '2023-01-19 14:31:29');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (200, '1b4cd1a2-41b0-4147-b3ac-ad003e443b44', 'single_choice', 'dfsgdsfg', 'dfsgdsfg', 126,
        '2023-01-19 14:31:29');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (201, 'c074edd6-064c-41b3-9c90-860c73e12183', 'single_choice', 'dfsg', 'dfsg', 195, '2023-01-19 14:31:29');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (202, 'cadae67a-28f9-4e19-bf87-24ce18b5ec73', 'single_choice', 'WWEEEEE', 'rtyerty', 198, '2023-01-19 14:31:29');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (205, '75699959-2a7b-41fe-8fe6-e858f350c0fe', 'single_choice', 'rstdy', 'trdhyrty', 166, '2023-01-19 14:31:29');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (206, 'ff3e70d1-d8da-433a-8884-ff2080b364bb', 'single_choice', 'gxhfd', 'fdgh', 173, '2023-01-19 14:31:29');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (207, '23df5f2a-a4ee-473c-aa1b-e7017881141f', 'single_choice', 'fghj', 'cghj', 186, '2023-01-19 14:31:29');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (208, '09ccc35a-c873-423b-a219-ef443d34e7aa', 'single_choice', 'gfdfsgdsfg', 'dsfgdsfg', 198,
        '2023-01-19 14:31:29');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (209, 'f15f3f17-8f2b-4959-b4fe-b71aabae393b', 'single_choice', 'rtyrty', 'dfghfdgh', 198, '2023-01-19 14:31:29');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (210, 'be971007-7c02-409c-a5a8-83c1388498d3', 'text', 'rrrrrrrrrrrr', 'rrrrrrrrrrrr', 198,
        '2023-01-19 14:31:29');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (211, 'ca893102-c754-4e82-9b9f-85203d86ef87', 'true_false', 'fdghghfdg', 'dfghgfdhfd', 198,
        '2023-01-19 14:31:29');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (212, 'd8cd5a94-5f34-487d-a2d9-f43d6e9f8424', 'text', 'Текст ', 'аенр', 199, '2023-01-19 14:31:29');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (213, '7d04a383-26e2-4c01-b382-0e32083aa47d', 'numerical', 'число', 'ывап', 199, '2023-01-19 14:31:29');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (214, '5367832e-ecb8-498a-9c76-82fade9bc638', 'true_false', 'тру фалсе', 'рпол', 199, '2023-01-19 14:31:29');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (215, 'eabd23f2-2a5a-4732-9678-0a61c17ae6d5', 'single_choice', 'эдиний', 'выап', 199, '2023-01-19 14:31:29');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (216, '26f01f59-3f37-4c83-895f-cd876038ed0c', 'multiple_choice', 'ваыпвыапвыа', 'ывап', 199,
        '2023-01-19 14:31:29');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (217, 'fcdc10fd-d0d0-41b8-948c-32779b7041a9', 'single_choice', 'jhk', 'gjhk', 202, '2023-01-27 21:03:13');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (218, '08178ce1-be85-40e5-adc3-5402b6459a19', 'single_choice', 'hjfghj', 'jrtyjut', 199, '2023-01-27 21:27:11');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (224, '31323822-2e0b-4e95-9996-6f8af3bec1b4', 'text', '1 test text', '1 test text', 204, '2023-02-23 20:02:04');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (225, 'cdc68a65-af9c-41a0-beb0-361559ca1b3a', 'text', '2 test text', '2 test text', 204, '2023-02-23 20:04:39');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (226, '1cd45337-906f-4e43-b267-9f87811f54fa', 'text', '3 test text', '', 204, '2023-02-23 20:05:23');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (227, 'a44ef229-c352-4b06-80d8-2011492bddcd', 'numerical', '1 test number', '1 test number', 204,
        '2023-02-23 20:06:15');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (228, 'f6139cc8-0d49-40fb-9801-097498ed1965', 'numerical', '2 test number', '2 test number', 204,
        '2023-02-23 20:07:47');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (229, '612aa694-b45a-4701-93d7-152473951a1a', 'single_choice', '1 test single', '1 test single', 204,
        '2023-02-23 21:18:40');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (230, '170d1c9e-c00d-401c-80a9-c8c39d4c71fd', 'true_false', '2 test true ', '', 204, '2023-02-23 21:19:26');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (231, '9ea361ac-91c9-4064-8206-3854583f43ec', 'single_choice', '2 test single', '2 test single', 204,
        '2023-02-23 21:20:08');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (232, 'a31c43f9-a2e2-4225-a668-d0db79e49fb7', 'single_choice', '2 test single', '2 test single', 204,
        '2023-02-23 21:20:25');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (233, 'cf2f38d1-9dab-44d2-9402-4badc70444be', 'single_choice', '3 test single', '3 test single', 204,
        '2023-02-23 21:20:43');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (234, 'c299979c-7f07-49cc-9520-8f706af4a2ce', 'single_choice', '4 test single', '4 test single', 204,
        '2023-02-23 22:06:41');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (236, '324ed9ad-6ff9-486f-903d-d9cf6e9a1068', 'single_choice', '6 test single', '6 test single', 204,
        '2023-02-23 22:09:06');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (237, '9695d258-6945-4f98-a577-ab8c13f9389e', 'multiple_choice', '1 test multi', '1 test multi', 204,
        '2023-02-23 22:10:34');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (238, 'ebc79a75-dba9-45b8-9388-9aa4cd0b553a', 'multiple_choice', '2 test multi', '', 204, '2023-02-23 22:10:57');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (239, '1f40a549-d77a-4280-a1ea-02b489e83ee5', 'multiple_choice', '3 test multi uuuuuuu', '3 test multi uuuuuu',
        204, '2023-02-23 22:19:08');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (242, '1bde3285-c669-4e75-bc33-c1d0122114d1', 'numerical', 'NUmberushka', 'NUmberushka', 199,
        '2023-02-23 23:15:46');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (243, 'd8495de9-3726-464a-bcd9-f44d6b01f25d', 'text', 'fghd', 'fdgh', 207, '2023-02-25 14:45:05');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (244, '9afb2579-904f-49b7-8301-b8cf8faf9245', 'text', 'Questio', 'lk;''', 208, '2023-02-25 16:27:01');
INSERT INTO public.question (id, uuid, type, content, description, test_id, created) OVERRIDING SYSTEM VALUE
VALUES (245, '12ec16d3-8f0f-43f3-b314-746d82413ae0', 'single_choice', 'vcbnbvc', 'cvnb', 208, '2023-02-25 16:28:32');

-- Population the answer table
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (1, '59f80841-5bcf-490d-a16c-620f59c0be1f',
        'Integer pede justo, lacinia eget, tincidunt eget, tempus vel, pede.', true, 1, '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (2, '146f59f5-7386-453d-8f1d-32162e751b08', 'Proin at turpis a pede posuere nonummy.', true, 2,
        '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (3, '93abf447-b270-423e-85ed-c460aeb2f600', 'Integer a nibh.', true, 3, '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (4, '6e519991-2c82-43ee-8a02-571f684d57e0', 'Nulla tempus.', true, 4, '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (5, '6cc80321-061c-430c-b780-283c16f024ac',
        'In tempor, turpis nec euismod scelerisque, quam turpis adipiscing lorem, vitae mattis nibh ligula nec sem.',
        true, 5, '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (6, '7b5362e7-0c18-4408-b631-1c885150311f', 'Vestibulum rutrum rutrum neque.', true, 6, '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (7, '5972b70e-2894-4a7e-898b-5ae00085584d',
        'Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus.', true, 7,
        '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (8, 'b6a18f08-8936-4b6e-94df-a96054393fa5', 'Nulla facilisi.', true, 8, '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (9, 'ef3d9d6a-d336-4374-9a2c-c2fa0ddb8780', 'Mauris lacinia sapien quis libero.', true, 9,
        '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (10, '3b571fe4-78be-4207-ac5a-d198c4d47854', 'Aliquam sit amet diam in magna bibendum imperdiet.', true, 10,
        '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (11, '482e2873-bc5e-4196-8fae-741570288405',
        'In tempor, turpis nec euismod scelerisque, quam turpis adipiscing lorem, vitae mattis nibh ligula nec sem.',
        true, 11, '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (12, '89e890a9-7f17-47a4-bdcc-b477e79cfdbe', 'Etiam pretium iaculis justo.', true, 12, '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (13, '76f1bc8d-663e-4467-ab67-a58b8bfefcca', 'Nam tristique tortor eu pede.', true, 13, '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (15, 'e00f1bef-a48c-48df-87a9-958203b139a3',
        'Fusce congue, diam id ornare imperdiet, sapien urna pretium nisl, ut volutpat sapien arcu sed augue.', true,
        15, '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (16, 'b87ebb2d-28f6-40a7-95e1-4c2e753ad576', 'Maecenas tincidunt lacus at velit.', true, 16,
        '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (17, '05240d5a-225d-446f-8718-2235ceef6e6d',
        'Vivamus metus arcu, adipiscing molestie, hendrerit at, vulputate vitae, nisl.', true, 17,
        '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (19, '46d4e095-b6ab-40d4-8c70-ea7b20b94ee0', 'Donec dapibus.', true, 19, '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (20, 'a63dbb1d-6f78-42c8-8244-e99bda34b952', 'Morbi porttitor lorem id ligula.', true, 20,
        '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (23, '0dc5ea3c-93ac-4920-a880-477ce7d9d3b2', 'In sagittis dui vel nisl.', true, 23, '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (24, '2979afce-94cc-43e7-a065-2e701a22200c', 'Integer tincidunt ante vel ipsum.', true, 24,
        '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (25, '4e11025e-58a9-4037-a30b-0636c87b61f9',
        'Nullam orci pede, venenatis non, sodales sed, tincidunt eu, felis.', true, 25, '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (26, '79ffffce-dbed-41d3-a52e-ca89c336946b', 'In quis justo.', true, 26, '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (27, '9944104f-6001-4c05-9c83-a4a52d67dac3', 'Nulla mollis molestie lorem.', true, 27, '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (28, '6a10ccc0-a3b7-4035-a1e7-abeee0105f2f', 'Suspendisse potenti.', true, 28, '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (29, '4282c77b-7063-4e72-bb42-fd8d671d5481', 'Cras pellentesque volutpat dui.', true, 29, '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (30, 'd79cefbc-2948-4fb6-8659-b9130a0e5096', 'Suspendisse potenti.', true, 30, '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (31, '1a9ababe-3e31-4358-b9cb-560330196139', 'Mauris ullamcorper purus sit amet nulla.', true, 31,
        '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (32, '9c0bac04-a5fa-4c5e-9077-b7c8c30d2618', 'Curabitur convallis.', true, 32, '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (33, '63582188-26fc-40a7-9ecb-67bdb0a4ab49', 'Maecenas rhoncus aliquam lacus.', true, 33, '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (34, '1ef24cc0-607c-4301-9786-4b44c92c8260', 'In hac habitasse platea dictumst.', true, 34,
        '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (35, '9debb044-c6ab-4338-be79-1617411f875b', 'Vestibulum sed magna at nunc commodo placerat.', true, 35,
        '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (36, '6084b9a0-ed74-4cb3-9f84-c549b0e13b6e', 'Pellentesque viverra pede ac diam.', true, 36,
        '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (37, '9bbe3316-d2bb-4dcc-9321-e438db63ca50', 'Aenean auctor gravida sem.', true, 37, '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (38, '7750035b-fbc6-4684-924a-61bb681fbaa2',
        'Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus.', true, 38,
        '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (39, '2755e87c-bd48-4862-a292-cfa427e4df69', 'Pellentesque ultrices mattis odio.', true, 39,
        '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (40, 'a131b845-88a9-4da0-8e67-d3795a3917a2', 'Etiam vel augue.', true, 40, '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (41, '24156b8c-3773-4718-99a3-e87c50e2d809', 'Aliquam quis turpis eget elit sodales scelerisque.', true, 41,
        '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (42, '4a13dbc1-9a60-426b-a441-70dc7a28b977', 'Donec dapibus.', true, 42, '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (43, 'b369012f-9070-4272-9bab-d00e22369b36', 'Nulla tempus.', true, 43, '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (44, '1476a639-0894-44e0-84ef-d431bfb1c841', 'Integer ac neque.', true, 44, '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (45, 'b81f2c97-7f1b-4813-a680-204c2dc2ac47', 'Praesent id massa id nisl venenatis lacinia.', true, 45,
        '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (46, '0f7ceb30-a555-49f5-8e03-40c0297187da', 'Cras non velit nec nisi vulputate nonummy.', true, 46,
        '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (47, '07df11fd-d50f-4992-8c16-0340a5eae4b1', 'Praesent lectus.', true, 47, '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (48, '2e231cfa-74cb-4681-bb84-220e04f3b7ce',
        'Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Nulla dapibus dolor vel est.',
        true, 48, '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (49, 'd7d3413d-c98e-4ba2-b022-b24aae7dabc7', 'Nulla ut erat id mauris vulputate elementum.', true, 49,
        '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (50, '3cfa8c2f-6064-48b0-b20e-e3e6bb91f237', 'Integer tincidunt ante vel ipsum.', true, 50,
        '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (51, '14e11277-7d77-4c37-bf6b-a842c42b9b55', 'Donec ut dolor.', true, 51, '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (52, 'c86a63f8-fb4b-4b7b-b839-ae59848dead2',
        'Mauris enim leo, rhoncus sed, vestibulum sit amet, cursus id, turpis.', true, 52, '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (53, 'ad1fee15-6071-4b1e-800a-2064c513c448',
        'Cras mi pede, malesuada in, imperdiet et, commodo vulputate, justo.', true, 53, '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (54, 'a51a393d-46c7-41b5-90b5-4b2cafce5085', 'Donec ut mauris eget massa tempor convallis.', true, 54,
        '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (55, '553181df-6fcc-4f02-b1d3-e332472869aa', 'Nulla suscipit ligula in lacus.', true, 55, '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (56, 'c17d59e1-f648-469f-89c6-ec65fb51adee', 'Proin risus.', true, 56, '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (57, 'd7a96120-5164-44d1-854e-276633b8d3f2', 'Duis at velit eu est congue elementum.', true, 57,
        '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (59, '86de7305-1163-4b46-8ef9-baedaf577773', 'Nulla justo.', true, 59, '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (60, 'd6a62163-7f7d-489d-9d40-0f720b583743', 'Aliquam sit amet diam in magna bibendum imperdiet.', true, 60,
        '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (61, '8db9fae4-2d1c-4b2c-852c-040d17315b1a', 'Integer ac leo.', true, 61, '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (62, '2a0bfd43-7a5f-480f-8050-93a98a7e83ab', 'Praesent lectus.', true, 62, '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (63, '02c1a7f0-2889-4dd7-9e34-03f1aaf8d3c0', 'Aliquam quis turpis eget elit sodales scelerisque.', true, 63,
        '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (64, 'dde21442-bd89-4d99-8fcc-93a04730c40b', 'Duis at velit eu est congue elementum.', true, 64,
        '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (65, 'dc4e41ce-f2a4-4ccc-ad44-5237b40f7be5', 'Maecenas rhoncus aliquam lacus.', true, 65, '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (66, 'af7bdda0-974d-4366-a7ef-2c59c8a53fbc', 'In hac habitasse platea dictumst.', true, 66,
        '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (67, '731770a8-8acb-445e-8ed6-5b72a07d6a3f', 'Etiam vel augue.', true, 67, '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (68, '831c6b5b-ed9a-436d-8677-19e5361d7ece', 'In hac habitasse platea dictumst.', true, 68,
        '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (69, '3fc2ff0d-6eab-4a95-bad8-f168dd0e126f', 'Morbi non lectus.', true, 69, '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (70, '80288b79-c33f-4231-9d27-813840a63e1b',
        'Quisque erat eros, viverra eget, congue eget, semper rutrum, nulla.', true, 70, '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (71, 'c60d42e9-3b08-4580-95df-bb1bc530a510', 'In blandit ultrices enim.', true, 71, '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (72, 'd4cd3fb4-2ad8-4e0b-af88-2dda13b3c09a', 'In est risus, auctor sed, tristique in, tempus sit amet, sem.',
        true, 72, '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (73, 'a621271c-c6e7-47b0-863c-84ca1c633427', 'Vivamus tortor.', true, 73, '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (74, '6fbfccd6-be1e-4d5d-a746-2520940d3982', 'Quisque arcu libero, rutrum ac, lobortis vel, dapibus at, diam.',
        true, 74, '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (75, '7bd82508-9316-4586-9c03-0d87b4ebbbf9',
        'Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus.', true, 75,
        '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (76, '34f91f5b-5dc0-4997-b9ea-0c89351ce8aa', 'Suspendisse potenti.', true, 76, '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (77, '1e3a1c9b-1332-4637-8553-6eee442351e4', 'Duis bibendum.', true, 77, '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (78, 'da31bcbb-1f85-4a41-b788-afdb7dae9db0', 'Etiam pretium iaculis justo.', true, 78, '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (79, 'b2470c41-f1dd-47e8-816d-dab27441f271',
        'Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Duis faucibus accumsan odio.',
        true, 79, '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (80, '49dae0d4-61f1-40eb-a72e-a670b487902b', 'Vivamus tortor.', true, 80, '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (81, 'c7c88235-8619-4c54-b261-8cb694a348ee', 'Nulla ac enim.', true, 81, '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (82, '3d9061ea-dc1f-431b-bb61-0a692bc2cd84', 'Nunc nisl.', true, 82, '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (83, 'c6a410ec-8302-4e37-94ef-0f676de3fb33', 'Mauris sit amet eros.', true, 83, '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (84, 'daa2573e-7565-4992-9da7-1865d2536623', 'In congue.', true, 84, '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (85, '999e81db-3c30-4c37-8c13-e7372a7221e9', 'Morbi a ipsum.', true, 85, '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (86, '87356584-6309-41bb-9889-2a942508613e', 'Quisque ut erat.', true, 86, '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (88, '8b2cdc79-2719-4874-bc44-047b38b15ff3', 'Integer ac leo.', true, 88, '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (90, '07a3f3d4-014b-451c-8304-dc0150308831', 'Curabitur gravida nisi at nibh.', true, 90, '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (91, 'b4d5a0f1-344a-406b-811d-38bc3609bd3f',
        'Integer aliquet, massa id lobortis convallis, tortor risus dapibus augue, vel accumsan tellus nisi eu orci.',
        true, 91, '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (92, '5e4d8702-2fe0-401c-8f5b-ecff1dba68c3',
        'Cras mi pede, malesuada in, imperdiet et, commodo vulputate, justo.', true, 92, '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (93, '89f56807-d955-4149-8116-ecf0df16947c', 'Phasellus id sapien in sapien iaculis congue.', true, 93,
        '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (94, 'e5017983-a99f-47b6-8c14-dae45f255662', 'Etiam pretium iaculis justo.', true, 94, '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (95, '9a02d0ac-d171-490d-85aa-29a83c79387c', 'Fusce lacus purus, aliquet at, feugiat non, pretium quis, lectus.',
        true, 95, '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (96, '22ef542c-5af2-4eca-9866-2b5af5db1213', 'In est risus, auctor sed, tristique in, tempus sit amet, sem.',
        true, 96, '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (97, 'a915dd50-1b65-41cc-8d9b-2df985e6a677', 'Mauris sit amet eros.', true, 97, '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (99, '7f71b8d0-cc58-4a62-bedb-53fb20b67771', 'Proin interdum mauris non ligula pellentesque ultrices.', true, 99,
        '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (100, 'f60f02d9-e169-4802-85c1-c9a8e25e8d7d',
        'Integer pede justo, lacinia eget, tincidunt eget, tempus vel, pede.', true, 100, '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (101, '89f514d6-7b12-4e64-a996-1cc6e85482b7', 'Ut tellus.', true, 101, '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (102, 'df611288-c730-46ce-8a51-2e532ef996db', 'Vivamus in felis eu sapien cursus vestibulum.', true, 102,
        '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (103, '284e65b1-a99f-4ae6-9401-73a59d9e0862', 'Mauris lacinia sapien quis libero.', true, 103,
        '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (104, 'bdb25952-5a14-48cb-9523-5f612e723877', 'Nullam sit amet turpis elementum ligula vehicula consequat.',
        true, 104, '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (105, 'cafe0915-43dc-4bd8-90b6-88fa1c10528b', 'Suspendisse ornare consequat lectus.', true, 105,
        '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (106, '837079a0-6040-449b-b479-fd017a219a02', 'Sed accumsan felis.', true, 106, '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (107, 'f3bb13cc-e1af-4053-a87b-a0ec690f93ef', 'Nulla suscipit ligula in lacus.', true, 107,
        '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (108, 'bd03c736-1bf9-49e1-81a2-eedcccf79111', 'Pellentesque ultrices mattis odio.', true, 108,
        '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (109, 'd4c76051-ac6a-451f-9485-11bf5e5209cc', 'Pellentesque viverra pede ac diam.', true, 109,
        '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (111, '1df663d6-4392-4ad4-ab19-fe3cc5a9c0ab', 'Duis consequat dui nec nisi volutpat eleifend.', true, 111,
        '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (112, '72ff0e84-60df-43d8-b767-a861fddc6a97', 'Nulla ac enim.', true, 112, '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (113, '442ac82c-0d90-4929-85c2-57025ee53df0', 'Donec dapibus.', true, 113, '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (114, 'cf01115a-59d4-40c7-a31c-c9f3d0e83454', 'Proin at turpis a pede posuere nonummy.', true, 114,
        '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (116, '5d91fb80-daa3-4cbe-91e6-6886a1f39aa3', 'Nulla nisl.', true, 116, '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (117, '92a09c4c-5133-496b-9fc0-e3ee74a98c05', 'Maecenas rhoncus aliquam lacus.', true, 117,
        '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (118, 'd18c2a65-89bd-4a14-97b1-416ea97eac47', 'Aenean sit amet justo.', true, 118, '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (119, 'd4aa6581-7b9d-4917-a114-176b170a8cee', 'Nam dui.', true, 119, '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (120, '37c03c26-9942-4c85-86c7-aca5222185b3', 'Morbi a ipsum.', true, 120, '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (121, 'f317eb73-840b-4aad-896b-d2f9db361d98', 'Proin leo odio, porttitor id, consequat in, consequat ut, nulla.',
        true, 121, '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (122, 'ae08570b-851a-45bd-82a4-31aed2499c0f', 'Vivamus tortor.', true, 122, '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (123, '7f2cdcee-91e0-4c1b-bea5-980fe48bb95d', 'Sed sagittis.', true, 123, '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (124, '4054ec4c-046d-408a-9ca7-05134e261019', 'Maecenas rhoncus aliquam lacus.', true, 124,
        '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (125, '68ce6357-5d07-493a-aa62-d9802a3e8fca', 'Donec vitae nisi.', true, 125, '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (126, '321090d3-5601-4cb6-90e1-ec2d7e2ba022', 'Nulla ut erat id mauris vulputate elementum.', true, 126,
        '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (127, '6bb99ab7-20a3-4557-a455-25d7cfdd7ff3', 'Nunc nisl.', true, 127, '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (128, '2907d261-793d-4838-bdea-b3cdda8fdecb', 'Mauris sit amet eros.', true, 128, '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (129, 'b1354a70-ee84-4e0b-94ec-66d5e07f4f78',
        'Nulla neque libero, convallis eget, eleifend luctus, ultricies eu, nibh.', true, 129, '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (130, 'f448e9cb-f31a-432e-9395-ab7b298a4d38', 'Nulla facilisi.', true, 130, '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (131, 'a810e3ec-363b-404d-b283-f7744248aa34',
        'Donec diam neque, vestibulum eget, vulputate ut, ultrices vel, augue.', false, 101, '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (132, 'ac132920-d8b0-47e8-93d6-5f6cfe5c48b3', 'Praesent lectus.', false, 102, '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (133, '67dc26bb-919a-40d7-b05c-5f7a44d5aae7', 'Cras pellentesque volutpat dui.', false, 103,
        '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (134, '6f18b47f-8317-47bd-a2b3-f1887ad52687', 'Aenean auctor gravida sem.', false, 104, '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (135, 'f0149387-9c34-4f8c-9134-9921bde9a034', 'Sed accumsan felis.', false, 105, '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (136, '316d4dfe-6544-4eca-97a2-61c9e1c4974a', 'Donec posuere metus vitae ipsum.', false, 106,
        '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (137, 'f25c38f4-e656-485a-aec8-696f5c2481f8', 'Quisque ut erat.', false, 107, '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (138, 'a851efdf-2a59-4122-9ac6-3a4c9183baf1', 'Pellentesque ultrices mattis odio.', false, 108,
        '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (139, '4bddcadb-7c54-4f88-8c6b-a37f75e3ac7f', 'Proin eu mi.', false, 109, '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (140, 'b17b2b29-4491-43a4-8deb-629ae4cb05a6', 'In hac habitasse platea dictumst.', false, 110,
        '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (141, '2f72ad68-839c-4bbe-85f6-5559e69427d4', 'Quisque id justo sit amet sapien dignissim vestibulum.', false,
        111, '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (142, '6e75ef2e-687c-4231-8e55-6263617354b2', 'Nulla nisl.', false, 112, '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (143, '1298cf8b-b6f5-4f60-8313-fa8b36d4a17d', 'Etiam faucibus cursus urna.', false, 113, '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (144, '68beca71-5d5d-4473-a667-6871e217b9b7', 'Vivamus tortor.', false, 114, '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (146, 'b72576f1-4476-42fa-b43f-c0660e36cecf', 'Maecenas pulvinar lobortis est.', false, 116,
        '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (147, '2b99f17d-9793-4eca-9d32-3e0f94ae0e76', 'Donec semper sapien a libero.', false, 117,
        '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (148, 'd3bacf97-8fea-4559-beda-8ca9225e1a9b', 'Morbi quis tortor id nulla ultrices aliquet.', false, 118,
        '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (149, 'aa57154b-01a1-41bb-8078-2aab8fc6b48e', 'Integer non velit.', false, 119, '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (150, '9ea8526b-b34d-4b90-bc74-d2539de84720', 'Cras in purus eu magna vulputate luctus.', false, 120,
        '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (151, '551d252e-cb62-489f-9b45-6a9a95909c46', 'Curabitur at ipsum ac tellus semper interdum.', false, 121,
        '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (152, 'ea8825f8-f6ee-49b3-b880-f389b7de2b3c', 'Etiam faucibus cursus urna.', false, 122, '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (153, '2bf2bcc4-b3bc-4c76-87cd-4ebb67a8c21e', 'Sed vel enim sit amet nunc viverra dapibus.', false, 123,
        '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (155, '75719286-9866-4c8a-8541-eb0ad8d78ef9',
        'Fusce lacus purus, aliquet at, feugiat non, pretium quis, lectus.', false, 125, '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (156, '62a7baa4-c13d-448e-b361-1b0a84087891',
        'Nullam orci pede, venenatis non, sodales sed, tincidunt eu, felis.', false, 126, '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (158, 'ec952160-84fd-45d2-bf5e-d979159b544c',
        'Integer aliquet, massa id lobortis convallis, tortor risus dapibus augue, vel accumsan tellus nisi eu orci.',
        false, 128, '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (157, 'f5f59dd3-59ba-4044-97d6-9b41856ffd6d', 'TRRRRR', true, 127, '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (159, '54f7db95-0add-45a2-9ad4-b756b233dfae', 'Aenean auctor gravida sem.', false, 129, '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (160, 'd6185859-c34c-432d-b419-613d7e794a93', 'Morbi vel lectus in quam fringilla rhoncus.', false, 130,
        '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (161, '8ba04b86-7c82-4bcb-9ab0-623802d3e1cf', '1357', true, 131, '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (162, 'aa74dca7-de4c-49f9-900e-82bc91b48271', '8701', true, 132, '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (163, '50347911-bc40-489c-a2e0-b17593dc6811', '6286', true, 133, '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (165, 'e1a7e6f4-0417-4648-8c6d-bbca7b628100', '8979', true, 135, '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (166, '69ebb837-fc29-433b-add8-49c4f998f17f', '5852', true, 136, '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (167, 'b980a7ef-1d39-4fd4-ade9-cd2f168854c2', '3578', true, 137, '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (168, '4da474e1-b22c-4576-87ea-a635e731baeb', '4206', true, 138, '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (169, '38984ca4-9449-47dd-b0ef-1586b2c8386f', '9811', true, 139, '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (170, '6d041103-ab88-41f8-b324-c924bf5e510b', '4270', true, 140, '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (171, '35283dbe-504d-4d0d-9f29-8304f2f80c7d', '9321', true, 141, '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (172, 'a192b788-f044-41a8-818b-d5d043f30e7a', '9167', true, 142, '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (173, 'c23aa86a-b13c-47c1-a82d-9cba3f285c44', '5702', true, 143, '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (174, 'ea9ca450-e9a6-4123-849f-606f274d81de', '8250', true, 144, '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (175, 'eef6a449-d256-4747-a6e1-9d77e5424d33', '1323', true, 145, '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (176, '9e86d5de-7a92-4167-bbc1-f16dcd219d5a', '1886', true, 146, '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (177, '9e699ccd-e4fc-4275-b0f3-b15836d94c89', '46', true, 147, '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (178, '925f9dea-5ace-44de-b6ee-c084b32cdf1d', '9796', true, 148, '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (179, 'a3280f0d-0240-4ca3-961a-1f35ad6acc69', '9364', true, 149, '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (180, '01588a18-36e6-44ef-a96d-5f35b5b35f53', '1634', true, 150, '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (181, '0eb82555-dbcb-48c8-ae4c-c02c419d4486', '7437', true, 151, '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (182, 'c4191682-52cd-4443-857f-83283a211f96', '6053', true, 152, '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (183, '652a2315-b98b-4fcb-b47e-2fbfd524724c', '1898', true, 153, '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (184, 'df4a08b7-6c3f-4c85-972e-ec88ca0e63b4', '7501', true, 154, '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (185, 'ed039324-2192-43af-b3c9-a8c38f68bc7c', '1153', true, 155, '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (186, 'a5338435-2f8a-426c-bb50-8935758252bf', '8463', true, 156, '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (187, '0ae4e0cc-4103-4921-853f-4b6098246d6c', '8554', true, 157, '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (188, '5a5e32af-bd5a-428a-8b7e-a6350442c7ad', '27', true, 158, '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (189, '711c7cc6-2b8d-4d42-9954-60df06bd4999', '3218', true, 159, '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (190, 'a25231f6-37ae-42df-ad61-7fc7a582e062', '2582', true, 160, '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (191, '05d7e396-d9bd-454c-9da3-3cc9a25f4810', 'something', true, 161, '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (192, '0d6d05dc-e4f6-4759-be29-201168ebae51', 'something', true, 162, '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (194, 'd476ca98-dcc6-4705-8587-3f4ca4c18e0f', 'something', true, 164, '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (195, '1958196f-fed9-4ee2-bc05-2a17998b03da', 'something', true, 165, '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (196, '1e61f400-7062-4b4d-b5d5-1448d9577ae5', 'something', true, 166, '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (198, 'a110f8ff-ab1b-4ef6-9101-63b2db7ba83f', 'something', true, 168, '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (199, '9ec2bd4c-a070-45c4-adc0-1b88e1fd7cd2', 'something', true, 169, '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (200, '9ea79c1f-5162-4bae-b867-9fd1159a5a29', 'something', true, 170, '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (201, '666829e5-b753-44bd-8cb7-6b5e69a9e15e', 'something', true, 171, '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (202, 'b06ca769-bff2-4cd7-ab86-973f742ff96f', 'something', true, 172, '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (203, '5dbc818b-3e7f-4a07-ad40-a4c44f8ce0b1', 'something', true, 173, '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (204, '162d2bad-9359-4139-8139-29036eda65a8', 'something', true, 174, '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (205, '388a1215-2c94-472d-a1a0-cfc06c6a9d78', 'something', true, 175, '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (206, 'a7b40799-e16a-4ac5-b2f2-43c6ca77c538', 'something', true, 176, '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (207, 'bfa28ffd-6ba8-4484-ac34-7169b94d4c8a', 'something', true, 177, '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (208, '64077ca1-0180-4d3c-b3cc-c5c69450c24a', 'something', true, 178, '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (209, '5d1630fa-eac5-413d-863a-f0d22c090bb4', 'something', true, 179, '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (210, '126647bc-7a2b-4656-a38f-d7d28baea17d', 'something', true, 180, '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (211, '8359943a-4c98-46fb-9abe-f3813751f8dd', 'something', true, 181, '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (212, '78b6edaf-5292-477d-a159-526857680172', 'something', true, 182, '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (213, '2a2659f9-e0a4-4865-92bd-9d92c3bf1301', 'something', true, 183, '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (214, '57741738-f2c4-400d-a77e-48f961952999', 'something', true, 184, '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (215, 'bc04c181-e11d-4d37-bc11-0d7c57360386', 'something', true, 185, '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (217, 'de3d1d4e-7662-457e-9bdc-0eed267543ff', 'something', true, 187, '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (218, 'ab057c69-e7b6-422f-904b-e48da5325755', 'something', true, 188, '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (219, '1c4f7bdb-979f-4e9c-89eb-a0b3ba0b2b85', 'something', true, 189, '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (220, 'ea5ed3ac-9b3e-4688-92ca-26909b88cfee', 'something', true, 190, '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (221, 'e8e433cf-6912-4701-a4e9-578195644af1', 'something', true, 190, '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (227, '5ac80144-c4b7-4c52-9a47-1de16f17aabc', 'fgh', true, 206, '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (228, 'f472e8fe-dbd3-4025-99d0-e2f6da3068c9', 'fgh', false, 206, '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (229, '1e929b6c-a2d4-462a-a965-d7281c2464c6', 'fgh', false, 206, '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (230, 'b06739fd-2b8f-4c15-be56-096dd09fde8a', 'truew', true, 207, '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (231, '69c40fe3-e02b-4708-b026-27db62622dd3', 'hgjhgj', false, 207, '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (254, '3ed4574c-80c1-40fc-9942-2d8f35974437', 'fdthrdy', true, 14, '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (234, '21ea9533-c493-4601-8c78-9b7fa79b61dd', 'sdfg', true, 208, '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (237, 'd4a7b7e5-b6ff-4ff3-ab86-a7d1f3b061c2', 'False222', false, 209, '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (255, 'e5d5b0dc-f330-490b-85b9-3ee9a5e062f4', 'rtedyu', true, 14, '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (256, 'c3d71cf7-6fe1-484d-bc4c-e00d9f2bad70', 'etryutr', true, 14, '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (257, '2d6a53e5-8f85-4403-b26d-30dd23f198d5', 'rety', true, 18, '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (236, 'c7eb71f4-8429-4fd5-8cee-2ce52044ffc8', 'False12', true, 209, '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (235, '1e84c831-fd68-47d5-91dc-c480a4f37ce0', 'Now false', false, 209, '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (258, '5b463dd3-4b9b-45ce-8e8a-eee99b9aa29d', 'erty', true, 18, '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (238, '9aeed923-3e18-41fb-832e-cb37af964dbd', '111111111111111', true, 194, '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (239, '04d594d4-af10-4a20-a0c5-49aad9cf00f2', 'vcgfnjdfh', true, 195, '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (240, '23c9101a-6255-41a1-af3e-a58d8b07af07', '', true, 204, '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (241, '7ce51f1e-f2ca-49ce-b5d2-197dbcc94769', '', false, 204, '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (242, '5a69a3e6-76a8-4eb0-beb4-176597b39097', '', false, 204, '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (243, 'bedee463-cf6a-46e4-b633-5a4c895ac118', 'ghfdjytr', true, 204, '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (244, '0b7c3186-c534-49ab-9995-6260949a91f2', 'rtyu', false, 204, '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (245, '18991f27-0a33-49c5-9597-7d7ca00fad24', 'ytu r', true, 204, '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (246, '0658f50e-3fad-41c8-b77c-333314ebf8b3', 'tryutry', false, 204, '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (247, '7d2437ef-8f26-4d14-8827-33be0a884d80', '', true, 204, '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (248, '6958141d-84f3-4bfb-bb53-8cb1f7744f80', '', false, 204, '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (249, 'd6960e61-ffb1-4889-8180-a36523df9fca', '', false, 204, '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (250, 'a9745508-2435-4204-85f4-08574a6e4e86', '457', true, 197, '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (251, 'aeb37a32-5c75-4b67-b4b1-035a97cc41c0', 'ert', true, 203, '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (252, 'b1293ccf-6a7d-459c-9fe1-3876e7ef1198', 'ert', false, 203, '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (253, '7c4c6124-68cd-49e3-88a2-3bb10d3eaf83', 'ert', false, 203, '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (259, 'e626e04b-af78-40cd-a09f-db79f2b17d8a', 'eryt', true, 18, '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (260, '557b348b-432d-430d-8baa-b50e388b9f9d', 'rtyu', true, 21, '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (261, '67c12d04-67b5-4544-9fb1-62ed628dea98', 'rtyu', true, 21, '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (262, 'c6e6c0b3-06ea-4976-9fd1-9c6b20ef04b7', 'tryu', true, 21, '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (263, '542c22e2-900c-4129-bca0-b05b89b9d35f', 'teyu', true, 21, '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (264, '29209a19-56a8-43c9-8568-35b5d61e2cbf', 'rtsgrygreye', true, 210, '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (265, 'b4cbf48e-1f9f-4906-a5d2-4c0a9eabfdbf', 'true', true, 211, '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (266, 'd411d89b-219c-4b2b-9847-5d767a1a701e', 'Текст', true, 212, '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (267, '95e41855-ed05-4938-bd1a-c31ca31c734e', '455', true, 213, '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (268, '25df1f48-8325-4c22-8aec-d3c9b315e137', 'true', true, 214, '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (269, '7585f188-4d22-4194-bf20-a9e579042020', 'ывапен', false, 215, '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (270, '03c38386-cf11-4627-a769-982e5578032b', 'ывапнег', true, 215, '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (271, '9764c2c7-3ebc-4de1-8c35-772b880be016', 'ывапку65е', false, 215, '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (272, '358f121b-4222-4966-90d7-25c76ae4505f', 'унекогнгл', true, 216, '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (273, '062071a1-9d02-4e17-accd-8726456873a0', 'ывап', true, 216, '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (274, '4a02317f-eb28-4bcd-ab94-3882d2e6568f', 'ывап', false, 216, '2023-01-19 14:29:59');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (275, '1662fed5-d6b3-4e5c-ad29-d3984b817abe', 'oip''', true, 217, '2023-01-27 21:03:13');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (276, '4764e426-37af-4903-ae23-388d0aeab144', 'iop[', false, 217, '2023-01-27 21:03:13');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (277, '66d2ef9b-7f5b-49f0-b17b-06d6a49d614a', 'uiho', false, 217, '2023-01-27 21:03:13');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (278, '92c4cff6-23a3-4fd2-9b54-2ae60ba6638f', 'tyujytutyur', false, 218, '2023-01-27 21:27:11');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (279, 'aa571be6-8241-4045-bfe0-6c1edbd7d796', 'tryu', true, 218, '2023-01-27 21:27:11');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (280, '2a106b3c-ec5a-48f0-b60f-c99e33a037cc', 'tyutyru', false, 218, '2023-01-27 21:27:11');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (281, '308e9fae-a37b-4699-a01d-b11fea81d296', 'tyh', true, 219, '2023-01-27 21:27:48');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (282, '61834e6a-3d8f-47c9-ae35-0d6eefa145a6', 'rtyu', true, 219, '2023-01-27 21:27:48');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (283, '6668714d-d5bd-4376-8f4b-625497521575', 'rtuy', true, 219, '2023-01-27 21:27:48');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (289, '4c060dc4-8b12-40e4-93bd-c62f30c1b2df', 'true', true, 230, '2023-02-23 21:19:26');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (293, 'e91ad197-7f75-4ef8-b6f4-fbec4a344764', '1', true, 236, '2023-02-23 22:09:06');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (288, '1506c23c-9491-4609-98dd-035dc9558665', '444', true, 228, '2023-02-23 20:07:47');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (308, '2c33bef1-bc3e-4fb9-bb50-92989ba838a5', '1', true, 242, '2023-02-23 23:15:46');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (290, '98b5f6fb-0b2c-4e54-8ea1-44381bf872d1', 'cv', false, 234, '2023-02-23 22:06:41');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (291, '24ed7e8c-1db5-4442-aa7c-c60c61dccf3b', 'cv 2', true, 234, '2023-02-23 22:06:41');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (284, 'c5e09e9a-820f-4244-85b8-db3bf760fe47', 'er6ujgk', true, 220, '2023-01-30 15:56:02');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (309, 'a80de14f-e077-43b4-a22a-a3e6bf61a3b2', 'Semen', true, 163, '2023-02-24 14:10:19');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (310, 'bc3f82e7-b64a-478e-8624-2fedf36e7f8e', '1', true, 134, '2023-02-24 14:11:11');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (311, '06488b5d-4e4c-488e-b53e-503fb085ba68', 'fgdh', false, 199, '2023-02-24 14:25:35');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (314, '13be4382-8255-4354-938a-ab9c3dc79f37', 'dfhg', false, 199, '2023-02-24 14:25:35');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (315, '73947090-ebc3-47d9-a670-c8b267eb205f', 'dfhg', false, 199, '2023-02-24 14:25:35');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (316, 'fb019372-2d3d-4f32-8176-f11a9d3acd32', 'gfhj', true, 239, '2023-02-24 14:26:10');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (317, '4702cfec-98c4-4abf-aa04-62cdddd1aef4', 'gfhj', true, 239, '2023-02-24 14:26:10');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (318, 'f73b3d6f-f18c-4b29-b6c5-8112341b672e', 'fgjh', true, 239, '2023-02-24 14:26:10');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (312, '435b2745-e95a-424f-b6cd-eec8f0dd5e26', 'dfgh', true, 199, '2023-02-24 14:25:35');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (320, 'a63521e5-9ce4-430d-a0ca-3f954af2915a', 'dfgghfdh', true, 244, '2023-02-25 16:28:00');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (321, '512cd649-28c2-4692-aa7d-b48fa6d3008c', 'dfgh', false, 245, '2023-02-25 16:29:04');
INSERT INTO public.answer (id, uuid, content, is_correct, question_id, created) OVERRIDING SYSTEM VALUE
VALUES (322, 'c26428d2-afee-479e-8292-39ccbb9c1dbb', 'dfhg true', true, 245, '2023-02-25 16:29:04');

-- Population the role table
INSERT INTO public.role (id, uuid, name, created) OVERRIDING SYSTEM VALUE
VALUES (1, '4070e682-ea66-420f-a3ad-ec9edb0d1d6e', 'admin', '2023-01-19 14:31:58');
INSERT INTO public.role (id, uuid, name, created) OVERRIDING SYSTEM VALUE
VALUES (2, '09c48a63-31e4-48c1-ab36-5c4b68a00d75', 'student', '2023-01-19 14:31:58');

-- Population the epam_user table
INSERT INTO public.epam_user (id, uuid, username, password, email, first_name, last_name, is_activated, role_id,
                              created) OVERRIDING SYSTEM VALUE
VALUES (1, '7c868036-4c45-49af-9528-364973d596fb', 'cmcgonigle0', 'Green', 'cmcgonigle0@ameblo.jp', 'Carmencita',
        'McGonigle', true, 2, '2023-01-19 14:30:29');
INSERT INTO public.epam_user (id, uuid, username, password, email, first_name, last_name, is_activated, role_id,
                              created) OVERRIDING SYSTEM VALUE
VALUES (3, 'cd4998e1-350f-4826-9c59-1c46d92eb8ad', 'aeagers2', 'Yellow', 'aeagers2@state.gov', 'Ailis', 'Eagers', true,
        2, '2023-01-19 14:30:29');
INSERT INTO public.epam_user (id, uuid, username, password, email, first_name, last_name, is_activated, role_id,
                              created) OVERRIDING SYSTEM VALUE
VALUES (5, 'c7cce3d0-9c13-4361-818b-dc73728c1569', 'nmerrin4', 'Crimson', 'nmerrin4@vkontakte.ru', 'Noni', 'Merrin',
        false, 2, '2023-01-19 14:30:29');
INSERT INTO public.epam_user (id, uuid, username, password, email, first_name, last_name, is_activated, role_id,
                              created) OVERRIDING SYSTEM VALUE
VALUES (6, 'ae53dd59-ca5f-42d9-bace-e3bfff061872', 'cplunket5', 'Violet', 'cplunket5@google.it', 'Clementine',
        'Plunket', true, 2, '2023-01-19 14:30:29');
INSERT INTO public.epam_user (id, uuid, username, password, email, first_name, last_name, is_activated, role_id,
                              created) OVERRIDING SYSTEM VALUE
VALUES (7, 'a3a87e3f-1b38-4894-adb1-4cb34c7982a6', 'dokenden6', 'Fuscia', 'dokenden6@apple.com', 'Darcy', 'Okenden',
        false, 2, '2023-01-19 14:30:29');
INSERT INTO public.epam_user (id, uuid, username, password, email, first_name, last_name, is_activated, role_id,
                              created) OVERRIDING SYSTEM VALUE
VALUES (8, '3402f66d-fc11-42c6-8d78-db4fc8256079', 'ealmond7', 'Mauv', 'ealmond7@themeforest.net', 'Enrico', 'Almond',
        false, 2, '2023-01-19 14:30:29');
INSERT INTO public.epam_user (id, uuid, username, password, email, first_name, last_name, is_activated, role_id,
                              created) OVERRIDING SYSTEM VALUE
VALUES (9, '40ccd465-5986-43db-8e4f-a2575c31823a', 'ebride8', 'Fuscia', 'ebride8@dyndns.org', 'Ericha', 'Bride', true,
        2, '2023-01-19 14:30:29');
INSERT INTO public.epam_user (id, uuid, username, password, email, first_name, last_name, is_activated, role_id,
                              created) OVERRIDING SYSTEM VALUE
VALUES (10, 'e99a49cb-b2e7-40bc-821c-ef79b22aca2f', 'cmccerery9', 'Yellow', 'cmccerery9@uiuc.edu', 'Cathrin',
        'McCerery', false, 2, '2023-01-19 14:30:29');
INSERT INTO public.epam_user (id, uuid, username, password, email, first_name, last_name, is_activated, role_id,
                              created) OVERRIDING SYSTEM VALUE
VALUES (12, '16dd5e51-8e74-43c5-9ca2-80d66b29743d', 'eevab', 'Violet', 'eevab@europa.eu', 'Emlyn', 'Eva', true, 2,
        '2023-01-19 14:30:29');
INSERT INTO public.epam_user (id, uuid, username, password, email, first_name, last_name, is_activated, role_id,
                              created) OVERRIDING SYSTEM VALUE
VALUES (13, '3db2fffc-4058-4066-9168-b2b0a437d3f3', 'bgiffkinsc', 'Turquoise', 'bgiffkinsc@utexas.edu', 'Benita',
        'Giffkins', true, 2, '2023-01-19 14:30:29');
INSERT INTO public.epam_user (id, uuid, username, password, email, first_name, last_name, is_activated, role_id,
                              created) OVERRIDING SYSTEM VALUE
VALUES (14, 'd80e8098-ed40-49e4-a1fb-b3fbd62e63de', 'gchisnalld', 'Violet', 'gchisnalld@ft.com', 'Gillian', 'Chisnall',
        true, 2, '2023-01-19 14:30:29');
INSERT INTO public.epam_user (id, uuid, username, password, email, first_name, last_name, is_activated, role_id,
                              created) OVERRIDING SYSTEM VALUE
VALUES (15, '3c6c4c7b-9303-434f-aea2-c57e10c36ff0', 'tsaysee', 'Teal', 'tsaysee@oakley.com', 'Townie', 'Sayse', false,
        2, '2023-01-19 14:30:29');
INSERT INTO public.epam_user (id, uuid, username, password, email, first_name, last_name, is_activated, role_id,
                              created) OVERRIDING SYSTEM VALUE
VALUES (16, 'db23ceb9-c07c-43f8-96d6-67924b1e74ee', 'gneevesf', 'Mauv', 'gneevesf@imageshack.us', 'Giles', 'Neeves',
        false, 2, '2023-01-19 14:30:29');
INSERT INTO public.epam_user (id, uuid, username, password, email, first_name, last_name, is_activated, role_id,
                              created) OVERRIDING SYSTEM VALUE
VALUES (19, '26e05f72-3d62-40c9-93b2-decfba42101b', 'glambdini', 'Indigo', 'glambdini@theatlantic.com', 'Georgena',
        'Lambdin', false, 2, '2023-01-19 14:30:29');
INSERT INTO public.epam_user (id, uuid, username, password, email, first_name, last_name, is_activated, role_id,
                              created) OVERRIDING SYSTEM VALUE
VALUES (20, '8055076a-a2e3-4797-aa18-d00f91d25ef2', 'atiddj', 'Red', 'atiddj@jiathis.com', 'Arlene', 'Tidd', false, 2,
        '2023-01-19 14:30:29');
INSERT INTO public.epam_user (id, uuid, username, password, email, first_name, last_name, is_activated, role_id,
                              created) OVERRIDING SYSTEM VALUE
VALUES (21, '65c6b57b-d0be-4637-a98d-fb6dde5a5114', 'theazelk', 'Aquamarine', 'theazelk@springer.com', 'Trixie',
        'Heazel', false, 2, '2023-01-19 14:30:29');
INSERT INTO public.epam_user (id, uuid, username, password, email, first_name, last_name, is_activated, role_id,
                              created) OVERRIDING SYSTEM VALUE
VALUES (22, '3244956c-9357-4db4-8d52-f9f47b518013', 'croxbroughl', 'Goldenrod', 'croxbroughl@tinypic.com', 'Clem',
        'Roxbrough', true, 2, '2023-01-19 14:30:29');
INSERT INTO public.epam_user (id, uuid, username, password, email, first_name, last_name, is_activated, role_id,
                              created) OVERRIDING SYSTEM VALUE
VALUES (23, '7bad4446-d557-4dd6-a076-7b2c88649973', 'dnoorem', 'Red', 'dnoorem@webs.com', 'Doretta', 'Noore', true, 2,
        '2023-01-19 14:30:29');
INSERT INTO public.epam_user (id, uuid, username, password, email, first_name, last_name, is_activated, role_id,
                              created) OVERRIDING SYSTEM VALUE
VALUES (24, '118b62cc-4c2a-4a8b-a40a-d5711f68ceab', 'lrapinettn', 'Violet', 'lrapinettn@fastcompany.com', 'Lorrie',
        'Rapinett', true, 2, '2023-01-19 14:30:29');
INSERT INTO public.epam_user (id, uuid, username, password, email, first_name, last_name, is_activated, role_id,
                              created) OVERRIDING SYSTEM VALUE
VALUES (25, '0a408d37-0799-4d0c-a846-184286c8a740', 'wheildso', 'Teal', 'wheildso@webnode.com', 'Wainwright', 'Heilds',
        false, 2, '2023-01-19 14:30:29');
INSERT INTO public.epam_user (id, uuid, username, password, email, first_name, last_name, is_activated, role_id,
                              created) OVERRIDING SYSTEM VALUE
VALUES (26, 'be0f7832-9394-47b3-99db-f2a68a2126b9', 'celpheep', 'Yellow', 'celpheep@yellowpages.com', 'Corie', 'Elphee',
        true, 2, '2023-01-19 14:30:29');
INSERT INTO public.epam_user (id, uuid, username, password, email, first_name, last_name, is_activated, role_id,
                              created) OVERRIDING SYSTEM VALUE
VALUES (27, '32da1caa-e9c8-42f0-8a7f-5305b62a4ef4', 'pconechieq', 'Maroon', 'pconechieq@marketwatch.com', 'Pearce',
        'Conechie', true, 2, '2023-01-19 14:30:29');
INSERT INTO public.epam_user (id, uuid, username, password, email, first_name, last_name, is_activated, role_id,
                              created) OVERRIDING SYSTEM VALUE
VALUES (29, '7e33e235-6b51-4a39-980d-83513d9d8cef', 'ggallens', 'Indigo', 'ggallens@scientificamerican.com', 'Guntar',
        'Gallen', true, 2, '2023-01-19 14:30:29');
INSERT INTO public.epam_user (id, uuid, username, password, email, first_name, last_name, is_activated, role_id,
                              created) OVERRIDING SYSTEM VALUE
VALUES (30, 'e96020ba-fc40-46b5-a79e-946fe90665f0', 'kmugglestonet', 'Indigo', 'kmugglestonet@usda.gov', 'Kelsi',
        'Mugglestone', true, 2, '2023-01-19 14:30:29');
INSERT INTO public.epam_user (id, uuid, username, password, email, first_name, last_name, is_activated, role_id,
                              created) OVERRIDING SYSTEM VALUE
VALUES (31, '98351761-22e3-4131-997c-acce0008f544', 'awynettu', 'Pink', 'awynettu@ycombinator.com', 'Amber', 'Wynett',
        true, 2, '2023-01-19 14:30:29');
INSERT INTO public.epam_user (id, uuid, username, password, email, first_name, last_name, is_activated, role_id,
                              created) OVERRIDING SYSTEM VALUE
VALUES (32, '109a740b-18fc-4bfb-ac7c-1f7309df5ea3', 'vcorcoranv', 'Puce', 'vcorcoranv@craigslist.org', 'Valera',
        'Corcoran', true, 2, '2023-01-19 14:30:29');
INSERT INTO public.epam_user (id, uuid, username, password, email, first_name, last_name, is_activated, role_id,
                              created) OVERRIDING SYSTEM VALUE
VALUES (33, '3e0e81e2-5e8e-45ea-9e84-0b68551747a2', 'pbennyw', 'Violet', 'pbennyw@yellowpages.com', 'Pembroke', 'Benny',
        true, 2, '2023-01-19 14:30:29');
INSERT INTO public.epam_user (id, uuid, username, password, email, first_name, last_name, is_activated, role_id,
                              created) OVERRIDING SYSTEM VALUE
VALUES (34, '283158cb-8543-49b4-8c2b-6f29f270612a', 'cpaskx', 'Green', 'cpaskx@prnewswire.com', 'Corene', 'Pask', false,
        2, '2023-01-19 14:30:29');
INSERT INTO public.epam_user (id, uuid, username, password, email, first_name, last_name, is_activated, role_id,
                              created) OVERRIDING SYSTEM VALUE
VALUES (35, '21213687-f6a3-4ce5-a81e-a06f40fde206', 'nkunzy', 'Violet', 'nkunzy@psu.edu', 'Nick', 'Kunz', true, 2,
        '2023-01-19 14:30:29');
INSERT INTO public.epam_user (id, uuid, username, password, email, first_name, last_name, is_activated, role_id,
                              created) OVERRIDING SYSTEM VALUE
VALUES (36, '86c7b848-27f8-4767-b8a5-eaf40a44e141', 'jcraykerz', 'Khaki', 'jcraykerz@rediff.com', 'Jerrie', 'Crayker',
        false, 2, '2023-01-19 14:30:29');
INSERT INTO public.epam_user (id, uuid, username, password, email, first_name, last_name, is_activated, role_id,
                              created) OVERRIDING SYSTEM VALUE
VALUES (37, '50a2b356-5450-4e19-af69-2d26f4ccba5f', 'cstithe10', 'Purple', 'cstithe10@privacy.gov.au', 'Clio', 'Stithe',
        true, 2, '2023-01-19 14:30:29');
INSERT INTO public.epam_user (id, uuid, username, password, email, first_name, last_name, is_activated, role_id,
                              created) OVERRIDING SYSTEM VALUE
VALUES (38, '10a8f7f9-1389-494d-8e45-e25876f61db1', 'shammelberg11', 'Fuscia', 'shammelberg11@scientificamerican.com',
        'Sebastian', 'Hammelberg', true, 2, '2023-01-19 14:30:29');
INSERT INTO public.epam_user (id, uuid, username, password, email, first_name, last_name, is_activated, role_id,
                              created) OVERRIDING SYSTEM VALUE
VALUES (39, '42d9cc55-e1b1-4afe-b05a-7cf4dbc52792', 'bwilcher12', 'Mauv', 'bwilcher12@devhub.com', 'Babs', 'Wilcher',
        true, 2, '2023-01-19 14:30:29');
INSERT INTO public.epam_user (id, uuid, username, password, email, first_name, last_name, is_activated, role_id,
                              created) OVERRIDING SYSTEM VALUE
VALUES (40, '9f5d05d1-7484-46ce-ab5b-fe1abf62adb0', 'mniave13', 'Fuscia', 'mniave13@dell.com', 'Madella', 'Niave', true,
        2, '2023-01-19 14:30:29');
INSERT INTO public.epam_user (id, uuid, username, password, email, first_name, last_name, is_activated, role_id,
                              created) OVERRIDING SYSTEM VALUE
VALUES (42, 'da0c4e23-235b-4f38-9a24-8e3120314987', 'bhinks15', 'Blue', 'bhinks15@aol.com', 'Bank', 'Hinks', false, 2,
        '2023-01-19 14:30:29');
INSERT INTO public.epam_user (id, uuid, username, password, email, first_name, last_name, is_activated, role_id,
                              created) OVERRIDING SYSTEM VALUE
VALUES (43, '7bc6e06d-d2d3-4a22-9b9b-7c65054248b5', 'dwiddowfield16', 'Puce', 'dwiddowfield16@imgur.com', 'Dannye',
        'Widdowfield', false, 2, '2023-01-19 14:30:29');
INSERT INTO public.epam_user (id, uuid, username, password, email, first_name, last_name, is_activated, role_id,
                              created) OVERRIDING SYSTEM VALUE
VALUES (44, '0594211d-f16e-4a6b-8164-5f7617dc1834', 'bingree17', 'Pink', 'bingree17@intel.com', 'Bobina', 'Ingree',
        false, 2, '2023-01-19 14:30:29');
INSERT INTO public.epam_user (id, uuid, username, password, email, first_name, last_name, is_activated, role_id,
                              created) OVERRIDING SYSTEM VALUE
VALUES (45, '73e6b7dc-6097-456c-86b3-ef86e57b42b5', 'eloughton18', 'Crimson', 'eloughton18@w3.org', 'Eryn', 'Loughton',
        true, 2, '2023-01-19 14:30:29');
INSERT INTO public.epam_user (id, uuid, username, password, email, first_name, last_name, is_activated, role_id,
                              created) OVERRIDING SYSTEM VALUE
VALUES (46, '9b248215-bbaa-4cbf-8a04-27214567fb7a', 'belward19', 'Indigo', 'belward19@paypal.com', 'Briana', 'Elward',
        true, 2, '2023-01-19 14:30:29');
INSERT INTO public.epam_user (id, uuid, username, password, email, first_name, last_name, is_activated, role_id,
                              created) OVERRIDING SYSTEM VALUE
VALUES (47, '2b239553-83d6-4f93-acc9-c66dbd38a00b', 'pbeedie1a', 'Maroon', 'pbeedie1a@wikipedia.org', 'Philippine',
        'Beedie', true, 2, '2023-01-19 14:30:29');
INSERT INTO public.epam_user (id, uuid, username, password, email, first_name, last_name, is_activated, role_id,
                              created) OVERRIDING SYSTEM VALUE
VALUES (48, 'da35b5f0-2eca-4441-822e-afad52d020e5', 'lbatcheldor1b', 'Maroon', 'lbatcheldor1b@myspace.com', 'Lucian',
        'Batcheldor', false, 2, '2023-01-19 14:30:29');
INSERT INTO public.epam_user (id, uuid, username, password, email, first_name, last_name, is_activated, role_id,
                              created) OVERRIDING SYSTEM VALUE
VALUES (49, '0ff8d089-8fbe-498e-8357-8ac0f7465db7', 'crodder1c', 'Mauv', 'crodder1c@sfgate.com', 'Codi', 'Rodder',
        false, 2, '2023-01-19 14:30:29');
INSERT INTO public.epam_user (id, uuid, username, password, email, first_name, last_name, is_activated, role_id,
                              created) OVERRIDING SYSTEM VALUE
VALUES (50, '44f2c157-cd52-47dd-b3bb-79e6221ff179', 'feytel1d', 'Yellow', 'feytel1d@example.com', 'Floyd', 'Eytel',
        false, 2, '2023-01-19 14:30:29');
INSERT INTO public.epam_user (id, uuid, username, password, email, first_name, last_name, is_activated, role_id,
                              created) OVERRIDING SYSTEM VALUE
VALUES (52, '5fe9c9ef-6b07-483c-a6de-7cbea4c94b23', 'zhovie1f', 'Pink', 'zhovie1f@nymag.com', 'Zilvia', 'Hovie', true,
        2, '2023-01-19 14:30:29');
INSERT INTO public.epam_user (id, uuid, username, password, email, first_name, last_name, is_activated, role_id,
                              created) OVERRIDING SYSTEM VALUE
VALUES (53, '5330440e-1bf5-458c-88d4-87f92ca360ca', 'pdelgadillo1g', 'Red', 'pdelgadillo1g@nytimes.com', 'Pippo',
        'Delgadillo', false, 2, '2023-01-19 14:30:29');
INSERT INTO public.epam_user (id, uuid, username, password, email, first_name, last_name, is_activated, role_id,
                              created) OVERRIDING SYSTEM VALUE
VALUES (54, '011f9964-9726-4813-b2c0-dddb611279d8', 'khurtic1h', 'Fuscia', 'khurtic1h@washington.edu', 'Kimbell',
        'Hurtic', true, 2, '2023-01-19 14:30:29');
INSERT INTO public.epam_user (id, uuid, username, password, email, first_name, last_name, is_activated, role_id,
                              created) OVERRIDING SYSTEM VALUE
VALUES (55, '01a181c4-92f0-40eb-bbca-eb85da06488c', 'bbleything1i', 'Puce', 'bbleything1i@tiny.cc', 'Berty',
        'Bleything', true, 2, '2023-01-19 14:30:29');
INSERT INTO public.epam_user (id, uuid, username, password, email, first_name, last_name, is_activated, role_id,
                              created) OVERRIDING SYSTEM VALUE
VALUES (56, 'c1fd1803-470e-4c66-bcec-1c641a1e3e4d', 'branvoise1j', 'Yellow', 'branvoise1j@ycombinator.com', 'Bil',
        'Ranvoise', false, 2, '2023-01-19 14:30:29');
INSERT INTO public.epam_user (id, uuid, username, password, email, first_name, last_name, is_activated, role_id,
                              created) OVERRIDING SYSTEM VALUE
VALUES (57, '48f2ff2d-28bd-4183-837c-ef134f1a6071', 'tliff1k', 'Indigo', 'tliff1k@cafepress.com', 'Toma', 'Liff', true,
        2, '2023-01-19 14:30:29');
INSERT INTO public.epam_user (id, uuid, username, password, email, first_name, last_name, is_activated, role_id,
                              created) OVERRIDING SYSTEM VALUE
VALUES (58, '7502f3f4-f723-4798-8d11-2e2595fa2b2b', 'mvivian1l', 'Yellow', 'mvivian1l@home.pl', 'Maurene', 'Vivian',
        false, 2, '2023-01-19 14:30:29');
INSERT INTO public.epam_user (id, uuid, username, password, email, first_name, last_name, is_activated, role_id,
                              created) OVERRIDING SYSTEM VALUE
VALUES (60, '0a7a4e58-1058-4563-821d-b0da3ca6cd89', 'mlettson1n', 'Blue', 'mlettson1n@tiny.cc', 'Murdock', 'Lettson',
        true, 2, '2023-01-19 14:30:29');
INSERT INTO public.epam_user (id, uuid, username, password, email, first_name, last_name, is_activated, role_id,
                              created) OVERRIDING SYSTEM VALUE
VALUES (61, 'ce09f1eb-b6e2-4132-a1f1-72b022f44f86', 'tcusick1o', 'Red', 'tcusick1o@t.co', 'Tallie', 'Cusick', false, 2,
        '2023-01-19 14:30:29');
INSERT INTO public.epam_user (id, uuid, username, password, email, first_name, last_name, is_activated, role_id,
                              created) OVERRIDING SYSTEM VALUE
VALUES (62, '0d7158c2-f3ee-41ca-8140-1343eb525ae8', 'lgillson1p', 'Orange', 'lgillson1p@alexa.com', 'Louie', 'Gillson',
        true, 2, '2023-01-19 14:30:29');
INSERT INTO public.epam_user (id, uuid, username, password, email, first_name, last_name, is_activated, role_id,
                              created) OVERRIDING SYSTEM VALUE
VALUES (63, '08b5795e-9206-4fdb-9e66-5a5cabb5412a', 'ameakes1q', 'Violet', 'ameakes1q@is.gd', 'Alayne', 'Meakes', false,
        2, '2023-01-19 14:30:29');
INSERT INTO public.epam_user (id, uuid, username, password, email, first_name, last_name, is_activated, role_id,
                              created) OVERRIDING SYSTEM VALUE
VALUES (64, '490a7789-1138-43e4-9bba-34a537a2a32c', 'twakeford1r', 'Fuscia', 'twakeford1r@hibu.com', 'Thorny',
        'Wakeford', false, 2, '2023-01-19 14:30:29');
INSERT INTO public.epam_user (id, uuid, username, password, email, first_name, last_name, is_activated, role_id,
                              created) OVERRIDING SYSTEM VALUE
VALUES (65, '9cfc686f-0127-4001-be8b-cac9c83d0b06', 'bludlom1s', 'Maroon', 'bludlom1s@youtube.com', 'Briney', 'Ludlom',
        false, 2, '2023-01-19 14:30:29');
INSERT INTO public.epam_user (id, uuid, username, password, email, first_name, last_name, is_activated, role_id,
                              created) OVERRIDING SYSTEM VALUE
VALUES (66, '2d1d23d2-ac4e-4aad-b974-ee72d7e4b123', 'ktees1t', 'Yellow', 'ktees1t@feedburner.com', 'Kermy', 'Tees',
        true, 2, '2023-01-19 14:30:29');
INSERT INTO public.epam_user (id, uuid, username, password, email, first_name, last_name, is_activated, role_id,
                              created) OVERRIDING SYSTEM VALUE
VALUES (67, '25764c68-6d55-4866-b217-659bc27af1b3', 'fpoytress1u', 'Goldenrod', 'fpoytress1u@biglobe.ne.jp', 'Florry',
        'Poytress', false, 2, '2023-01-19 14:30:29');
INSERT INTO public.epam_user (id, uuid, username, password, email, first_name, last_name, is_activated, role_id,
                              created) OVERRIDING SYSTEM VALUE
VALUES (68, '62468599-e3c2-4414-bd96-3c2b4099c418', 'gtonkes1v', 'Yellow', 'gtonkes1v@howstuffworks.com', 'Garik',
        'Tonkes', false, 2, '2023-01-19 14:30:29');
INSERT INTO public.epam_user (id, uuid, username, password, email, first_name, last_name, is_activated, role_id,
                              created) OVERRIDING SYSTEM VALUE
VALUES (69, '30bc599f-9b59-4a4e-b685-1ee64f410fff', 'cbrouncker1w', 'Yellow', 'cbrouncker1w@elpais.com', 'Cecil',
        'Brouncker', false, 2, '2023-01-19 14:30:29');
INSERT INTO public.epam_user (id, uuid, username, password, email, first_name, last_name, is_activated, role_id,
                              created) OVERRIDING SYSTEM VALUE
VALUES (59, 'f59f398d-4412-498d-9ee5-b803862ce68e', 'reeeeeee',
        '$2a$12$qAtCocaT7PjwOJbFFZhCkOVrRioasNfommv98NU453KbTP0engXTK', 'reeeeeee@webnode.com', 'reeeeeeeяяяяґґґґ',
        'reeeeeeeяяяяґґґґ', false, 2, '2023-01-19 14:30:29');
INSERT INTO public.epam_user (id, uuid, username, password, email, first_name, last_name, is_activated, role_id,
                              created) OVERRIDING SYSTEM VALUE
VALUES (2, 'f8657130-de17-466d-8edc-b99843d5f6f1', 'sdfghfd',
        '$2a$12$gIO6G0oNwM/prrAcsMubdeiF33QSp/XfzyUzvCkQyINgpRlO2vzAm', 'sdfghfd@house.gov', 'dfg', 'dfg', false, 2,
        '2023-01-19 14:30:29');
INSERT INTO public.epam_user (id, uuid, username, password, email, first_name, last_name, is_activated, role_id,
                              created) OVERRIDING SYSTEM VALUE
VALUES (28, 'f0e318d9-d5ee-4d92-b982-1174d1080218', 'іваіва', 'Red', 'sadas@myspace.com', 'StefaRomaіяйуцкенгш',
        'StefaRomaіяйуцкенгш', true, 2, '2023-01-19 14:30:29');
INSERT INTO public.epam_user (id, uuid, username, password, email, first_name, last_name, is_activated, role_id,
                              created) OVERRIDING SYSTEM VALUE
VALUES (51, 'baffb3a6-0cb8-4195-b944-a9f5cba941a6', 'olenka', 'Olenka123', 'olenka@olenka.olenka', 'olenka', 'olenka',
        true, 2, '2023-01-19 14:30:29');
INSERT INTO public.epam_user (id, uuid, username, password, email, first_name, last_name, is_activated, role_id,
                              created) OVERRIDING SYSTEM VALUE
VALUES (11, '83cea861-969a-4cf7-80ba-ee689ab983be', 'predshawa', 'Khaki', 'predshawa@toplist.cz', 'RRRRR', 'RRRRR',
        true, 2, '2023-01-19 14:30:29');
INSERT INTO public.epam_user (id, uuid, username, password, email, first_name, last_name, is_activated, role_id,
                              created) OVERRIDING SYSTEM VALUE
VALUES (4, '00e0d7bb-b9e9-49ff-bf8b-31a947ed3a8b', 'pzarfai3', 'Mauv', 'pzarfai3@engadget.com', 'AAAAAAAAAAAA',
        'AAAAAAAA', true, 2, '2023-01-19 14:30:29');
INSERT INTO public.epam_user (id, uuid, username, password, email, first_name, last_name, is_activated, role_id,
                              created) OVERRIDING SYSTEM VALUE
VALUES (70, 'c248b3d3-ac1f-4e02-895d-0d1554a96209', 'kaubri1x', 'Indigo', 'kaubri1x@latimes.com', 'Keri', 'Aubri', true,
        2, '2023-01-19 14:30:29');
INSERT INTO public.epam_user (id, uuid, username, password, email, first_name, last_name, is_activated, role_id,
                              created) OVERRIDING SYSTEM VALUE
VALUES (71, 'db6fa008-9cbd-4430-b9f2-7fc3995e3a53', 'cbarme1y', 'Orange', 'cbarme1y@tripadvisor.com', 'Cheryl', 'Barme',
        false, 2, '2023-01-19 14:30:29');
INSERT INTO public.epam_user (id, uuid, username, password, email, first_name, last_name, is_activated, role_id,
                              created) OVERRIDING SYSTEM VALUE
VALUES (72, '6de2e3c4-1788-4a7a-bfa2-2fcb15bc3ec1', 'dwallwood1z', 'Pink', 'dwallwood1z@xrea.com', 'Darda', 'Wallwood',
        false, 2, '2023-01-19 14:30:29');
INSERT INTO public.epam_user (id, uuid, username, password, email, first_name, last_name, is_activated, role_id,
                              created) OVERRIDING SYSTEM VALUE
VALUES (73, '3e97a1cd-82e0-4981-9af6-7a279efde908', 'wwelton20', 'Maroon', 'wwelton20@va.gov', 'Wang', 'Welton', false,
        2, '2023-01-19 14:30:29');
INSERT INTO public.epam_user (id, uuid, username, password, email, first_name, last_name, is_activated, role_id,
                              created) OVERRIDING SYSTEM VALUE
VALUES (74, '882a412f-f518-427c-9b6e-c72991c93cae', 'mrabley21', 'Goldenrod', 'mrabley21@shop-pro.jp', 'Martie',
        'Rabley', true, 2, '2023-01-19 14:30:29');
INSERT INTO public.epam_user (id, uuid, username, password, email, first_name, last_name, is_activated, role_id,
                              created) OVERRIDING SYSTEM VALUE
VALUES (75, 'bbc033b0-f34c-4684-b3c7-db87841ca239', 'oworvill22', 'Crimson', 'oworvill22@businessweek.com', 'Odette',
        'Worvill', true, 2, '2023-01-19 14:30:29');
INSERT INTO public.epam_user (id, uuid, username, password, email, first_name, last_name, is_activated, role_id,
                              created) OVERRIDING SYSTEM VALUE
VALUES (76, '88a81a2a-1f6c-47c2-9afc-e62414a0e9e7', 'gmcclounan23', 'Aquamarine', 'gmcclounan23@hc360.com', 'Gus',
        'McClounan', true, 2, '2023-01-19 14:30:29');
INSERT INTO public.epam_user (id, uuid, username, password, email, first_name, last_name, is_activated, role_id,
                              created) OVERRIDING SYSTEM VALUE
VALUES (77, '14393702-5197-4787-9dfa-4e98f57d97e9', 'akleinfeld24', 'Fuscia', 'akleinfeld24@arstechnica.com',
        'Antonius', 'Kleinfeld', false, 2, '2023-01-19 14:30:29');
INSERT INTO public.epam_user (id, uuid, username, password, email, first_name, last_name, is_activated, role_id,
                              created) OVERRIDING SYSTEM VALUE
VALUES (78, '8dbc57ae-c364-4a7e-a15c-e6f08f6f4720', 'rtudgay25', 'Blue', 'rtudgay25@squarespace.com', 'Robby', 'Tudgay',
        true, 2, '2023-01-19 14:30:29');
INSERT INTO public.epam_user (id, uuid, username, password, email, first_name, last_name, is_activated, role_id,
                              created) OVERRIDING SYSTEM VALUE
VALUES (79, 'dda8feed-c827-4399-8d98-479bd1c790cf', 'kgrindall26', 'Green', 'kgrindall26@seattletimes.com', 'Kevin',
        'Grindall', false, 2, '2023-01-19 14:30:29');
INSERT INTO public.epam_user (id, uuid, username, password, email, first_name, last_name, is_activated, role_id,
                              created) OVERRIDING SYSTEM VALUE
VALUES (80, '0733bca2-af3e-49d1-b184-dbc89265d1cc', 'bkwietek27', 'Turquoise', 'bkwietek27@unesco.org', 'Bernelle',
        'Kwietek', true, 2, '2023-01-19 14:30:29');
INSERT INTO public.epam_user (id, uuid, username, password, email, first_name, last_name, is_activated, role_id,
                              created) OVERRIDING SYSTEM VALUE
VALUES (81, '6c424e25-3af7-41eb-ab6b-64ce05dbf92c', 'gdewsbury28', 'Turquoise', 'gdewsbury28@ovh.net', 'Guido',
        'Dewsbury', false, 2, '2023-01-19 14:30:29');
INSERT INTO public.epam_user (id, uuid, username, password, email, first_name, last_name, is_activated, role_id,
                              created) OVERRIDING SYSTEM VALUE
VALUES (82, 'da1d771b-2366-4ac3-9b16-1d9c26b239b9', 'taberkirdo29', 'Blue', 'taberkirdo29@shop-pro.jp', 'Theresina',
        'Aberkirdo', false, 2, '2023-01-19 14:30:29');
INSERT INTO public.epam_user (id, uuid, username, password, email, first_name, last_name, is_activated, role_id,
                              created) OVERRIDING SYSTEM VALUE
VALUES (83, '70e5b280-eeae-4396-8e7c-0e607f0d259d', 'tdevall2a', 'Khaki', 'tdevall2a@dailymail.co.uk', 'Taylor',
        'Devall', true, 2, '2023-01-19 14:30:29');
INSERT INTO public.epam_user (id, uuid, username, password, email, first_name, last_name, is_activated, role_id,
                              created) OVERRIDING SYSTEM VALUE
VALUES (84, '3a6733bd-f75e-49b4-8d3e-0987d3673b78', 'emcorkil2b', 'Turquoise', 'emcorkil2b@nps.gov', 'Eve', 'McOrkil',
        true, 2, '2023-01-19 14:30:29');
INSERT INTO public.epam_user (id, uuid, username, password, email, first_name, last_name, is_activated, role_id,
                              created) OVERRIDING SYSTEM VALUE
VALUES (85, '84f55806-b018-4ba1-b22d-3fff5145f728', 'dbingell2c', 'Purple', 'dbingell2c@unblog.fr', 'Demetra',
        'Bingell', true, 2, '2023-01-19 14:30:29');
INSERT INTO public.epam_user (id, uuid, username, password, email, first_name, last_name, is_activated, role_id,
                              created) OVERRIDING SYSTEM VALUE
VALUES (86, '6e9bd508-5836-4d87-bf9f-82a73d26c974', 'nscaysbrook2d', 'Maroon', 'nscaysbrook2d@shinystat.com', 'Normand',
        'Scaysbrook', false, 2, '2023-01-19 14:30:29');
INSERT INTO public.epam_user (id, uuid, username, password, email, first_name, last_name, is_activated, role_id,
                              created) OVERRIDING SYSTEM VALUE
VALUES (87, '7e90d6f7-42e7-4118-85e5-4378e4780dd5', 'aandreotti2e', 'Aquamarine', 'aandreotti2e@yolasite.com', 'Arny',
        'Andreotti', false, 2, '2023-01-19 14:30:29');
INSERT INTO public.epam_user (id, uuid, username, password, email, first_name, last_name, is_activated, role_id,
                              created) OVERRIDING SYSTEM VALUE
VALUES (88, '649870db-c46d-4596-b769-2e3706c79923', 'dmacdonell2f', 'Pink', 'dmacdonell2f@i2i.jp', 'Dynah', 'MacDonell',
        false, 2, '2023-01-19 14:30:29');
INSERT INTO public.epam_user (id, uuid, username, password, email, first_name, last_name, is_activated, role_id,
                              created) OVERRIDING SYSTEM VALUE
VALUES (89, 'a3ccf3f6-e4c0-4083-b4af-936a242b204f', 'fsieve2g', 'Turquoise', 'fsieve2g@europa.eu', 'Fifine', 'Sieve',
        false, 2, '2023-01-19 14:30:29');
INSERT INTO public.epam_user (id, uuid, username, password, email, first_name, last_name, is_activated, role_id,
                              created) OVERRIDING SYSTEM VALUE
VALUES (90, 'b39d2ed3-bf7f-4b6e-932f-e8f9cba89675', 'msturdy2h', 'Yellow', 'msturdy2h@i2i.jp', 'Marthe', 'Sturdy', true,
        2, '2023-01-19 14:30:29');
INSERT INTO public.epam_user (id, uuid, username, password, email, first_name, last_name, is_activated, role_id,
                              created) OVERRIDING SYSTEM VALUE
VALUES (91, '38dd01f8-c1aa-4336-a9ec-3107e9f8dccb', 'vjeans2i', 'Khaki', 'vjeans2i@senate.gov', 'Vergil', 'Jeans',
        false, 2, '2023-01-19 14:30:29');
INSERT INTO public.epam_user (id, uuid, username, password, email, first_name, last_name, is_activated, role_id,
                              created) OVERRIDING SYSTEM VALUE
VALUES (92, '54c4a083-143d-4208-93ef-d0541a2f9bb3', 'rduffett2j', 'Mauv', 'rduffett2j@bloglines.com', 'Romona',
        'Duffett', false, 2, '2023-01-19 14:30:29');
INSERT INTO public.epam_user (id, uuid, username, password, email, first_name, last_name, is_activated, role_id,
                              created) OVERRIDING SYSTEM VALUE
VALUES (93, 'bb07efc7-f413-491e-ab8b-d89f3ea5a85b', 'sready2k', 'Purple', 'sready2k@eepurl.com', 'Sander', 'Ready',
        true, 2, '2023-01-19 14:30:29');
INSERT INTO public.epam_user (id, uuid, username, password, email, first_name, last_name, is_activated, role_id,
                              created) OVERRIDING SYSTEM VALUE
VALUES (94, '16ec5aaf-bb09-4b9b-88ac-c80257432f46', 'gsatyford2l', 'Khaki', 'gsatyford2l@cloudflare.com', 'Gabie',
        'Satyford', true, 2, '2023-01-19 14:30:29');
INSERT INTO public.epam_user (id, uuid, username, password, email, first_name, last_name, is_activated, role_id,
                              created) OVERRIDING SYSTEM VALUE
VALUES (95, 'bd356222-ee0a-4737-83af-fda4a3f1bda9', 'rarnecke2m', 'Crimson', 'rarnecke2m@adobe.com', 'Robby', 'Arnecke',
        true, 2, '2023-01-19 14:30:29');
INSERT INTO public.epam_user (id, uuid, username, password, email, first_name, last_name, is_activated, role_id,
                              created) OVERRIDING SYSTEM VALUE
VALUES (96, '34e426c0-f059-4942-8e60-9029a44f6671', 'kgleaves2n', 'Crimson', 'kgleaves2n@netlog.com', 'Kimberlee',
        'Gleaves', true, 2, '2023-01-19 14:30:29');
INSERT INTO public.epam_user (id, uuid, username, password, email, first_name, last_name, is_activated, role_id,
                              created) OVERRIDING SYSTEM VALUE
VALUES (97, '2f362571-dfb5-4779-a256-77fc6805d3a2', 'ngotcliffe2o', 'Mauv', 'ngotcliffe2o@diigo.com', 'Norris',
        'Gotcliffe', false, 2, '2023-01-19 14:30:29');
INSERT INTO public.epam_user (id, uuid, username, password, email, first_name, last_name, is_activated, role_id,
                              created) OVERRIDING SYSTEM VALUE
VALUES (98, '90cff7e0-8ebf-4b6b-91b3-afede494d276', 'tputtock2p', 'Indigo', 'tputtock2p@ning.com', 'Titos', 'Puttock',
        false, 2, '2023-01-19 14:30:29');
INSERT INTO public.epam_user (id, uuid, username, password, email, first_name, last_name, is_activated, role_id,
                              created) OVERRIDING SYSTEM VALUE
VALUES (99, '36be9fb8-19b5-4c6d-acc5-c6f9024d70f7', 'hmalins2q', 'Violet', 'hmalins2q@stumbleupon.com', 'Hilton',
        'Malins', false, 2, '2023-01-19 14:30:29');
INSERT INTO public.epam_user (id, uuid, username, password, email, first_name, last_name, is_activated, role_id,
                              created) OVERRIDING SYSTEM VALUE
VALUES (100, '5c5de62c-02e5-4d73-8d97-60cbbe1bb859', 'amacdermott2r', 'Orange', 'amacdermott2r@usatoday.com', 'Andres',
        'MacDermott', true, 2, '2023-01-19 14:30:29');
INSERT INTO public.epam_user (id, uuid, username, password, email, first_name, last_name, is_activated, role_id,
                              created) OVERRIDING SYSTEM VALUE
VALUES (101, '3a516d00-5d56-4f5f-88c2-1bb7339fd8ce', 'romanasd',
        '$2a$10$yvV28Sd0mxUndGiwD7bufuK/efeSDJJlHw/Z65AbZZDiRFl/3lJuC', 'roman@asd.asd', 'sdf', 'sdf', true, 2,
        '2023-01-19 14:30:29');
INSERT INTO public.epam_user (id, uuid, username, password, email, first_name, last_name, is_activated, role_id,
                              created) OVERRIDING SYSTEM VALUE
VALUES (102, '7a14c7c6-d874-4648-99f0-3e70fc580da5', 'sdafsd',
        '$2a$10$PPEOHl6AxSIJm40om9oSouDcNxjqSflwoHykhF6pY6uLie.wN4WPq', 'ds@dsf.sdf', 'kil', 'jhk', true, 2,
        '2023-01-19 14:30:29');
INSERT INTO public.epam_user (id, uuid, username, password, email, first_name, last_name, is_activated, role_id,
                              created) OVERRIDING SYSTEM VALUE
VALUES (103, '4ea3ca03-743a-4c20-b6ad-466847d477e4', 'admin',
        '$2a$10$2Fsic3noPODBNL25ZjRhZeF.5ypN2YypXF.eapbd78fl7NyzFmiDq', 'admin@sdf.sdf', 'dsf', 'sdf', true, 2,
        '2023-01-19 14:30:29');
INSERT INTO public.epam_user (id, uuid, username, password, email, first_name, last_name, is_activated, role_id,
                              created) OVERRIDING SYSTEM VALUE
VALUES (104, '613d9e9f-ba40-4bc1-9797-c9645adaa40d', 'sdfsa',
        '$2a$10$9jNevRlK9UjPXdi/FObjBOUOhBhmSEOiUmgegk3AP56QCJFh1Mg3G', 'sdfsa@sdf.sdf', 'fgdh', 'gfh', true, 2,
        '2023-01-19 14:30:29');
INSERT INTO public.epam_user (id, uuid, username, password, email, first_name, last_name, is_activated, role_id,
                              created) OVERRIDING SYSTEM VALUE
VALUES (105, '383cba12-3ee6-4944-9d25-de29235b728c', 'fdgdfg',
        '$2a$12$C5H.aJBlm0oB.jyEJ17wUOoLO9Aonv/Jwr9eRf1PGJRyIkJg/vOq.', 'sdfds@sdf.sdf', 'dsf', 'sadfsdgffgdh', true, 2,
        '2023-01-19 14:30:29');
INSERT INTO public.epam_user (id, uuid, username, password, email, first_name, last_name, is_activated, role_id,
                              created) OVERRIDING SYSTEM VALUE
VALUES (106, '680c8532-58ec-40a8-a670-ce16ce2ff513', 'student',
        '$2a$12$MlZUueZqpxe/8S6OWSK45OK0K9jVGT0CfnC7aOyQhlOqZsI2cHfge', 'student@sdf.ds', 'roma', 'roma', true, 2,
        '2023-01-19 14:30:29');
INSERT INTO public.epam_user (id, uuid, username, password, email, first_name, last_name, is_activated, role_id,
                              created) OVERRIDING SYSTEM VALUE
VALUES (17, 'f1cf4105-8a28-490a-9a21-8caa070b9f61', 'ccolesgdfgdfgdf',
        '$2a$12$65ztFQb1lUSgR.uzGoEn3e2NyfmPiDH47/0JmVcpln8U6pyj58zFq', 'ccolesg@de.vu', 'Catherinesdfds', 'Colessdfs',
        false, 2, '2023-01-19 14:30:29');
INSERT INTO public.epam_user (id, uuid, username, password, email, first_name, last_name, is_activated, role_id,
                              created) OVERRIDING SYSTEM VALUE
VALUES (41, 'eb2eeb93-83c1-4ae3-8a6a-7b29e8cd6430', 'dfsfdsdfdsdfsdfsdfsdfsdfsdsf', 'Purple',
        'dfsfdsdfdsdfsdfsdfsdfsdfsdsf@gmpg.org', 'YakubovichMylo', 'YakubovichMylo', true, 2, '2023-01-19 14:30:29');
INSERT INTO public.epam_user (id, uuid, username, password, email, first_name, last_name, is_activated, role_id,
                              created) OVERRIDING SYSTEM VALUE
VALUES (18, 'fcca7c9e-4bdc-4137-9615-033dd86203a3', 'qqqqqqqqqqqqqqqqqqjjjj', 'Roman123',
        'qqqqqqqqqqdsfsdddsfqqq@hc360.com', 'romasmdsad', 'romasmdsad', true, 2, '2023-01-19 14:30:29');
INSERT INTO public.epam_user (id, uuid, username, password, email, first_name, last_name, is_activated, role_id,
                              created) OVERRIDING SYSTEM VALUE
VALUES (108, '9ccc2e53-a4ed-485a-ad51-d24ce13e550f', 'std',
        '$2a$12$mPQ75v6Fw91uX/q4a2mY6eLG7ASP.9UwiIyeZQYCd8eYHM62w/IjS', 'roman@sd.sdf', 'sdf', 'sadf', true, 2,
        '2023-01-19 14:30:29');
INSERT INTO public.epam_user (id, uuid, username, password, email, first_name, last_name, is_activated, role_id,
                              created) OVERRIDING SYSTEM VALUE
VALUES (109, '83857bb9-891d-4c15-8589-ee4643b1b4e7', 'stude',
        '$2a$12$RccCH7gygTduroskrKZye.A3VSiP0EPdWgYHfqToBHIcfsfoW7LOy', 'stude@sf.sda', 'sdfdfghdfghdfdgh',
        'dfghdhfgfdghghfgfhgfhfgf', true, 2, '2023-01-20 00:40:54');
INSERT INTO public.epam_user (id, uuid, username, password, email, first_name, last_name, is_activated, role_id,
                              created) OVERRIDING SYSTEM VALUE
VALUES (110, 'c00eb8b2-23b0-40a4-a0d3-caab8a14db12', 'fff',
        '$2a$12$FvyXD6lLX7AzLd1xEisdp.sH0B2P9YQa7E3WWrsurGatwZ7Lq45tm', 'fff@sdf.sdaa', 'roman', 'liubymenko', true, 2,
        '2023-01-20 18:48:54');
INSERT INTO public.epam_user (id, uuid, username, password, email, first_name, last_name, is_activated, role_id,
                              created) OVERRIDING SYSTEM VALUE
VALUES (131, 'c6942d66-3b3c-4297-8cbf-cd0767e1cd9c', 'tytyyttttyyt',
        '$2a$12$y3.eECMGWIzuJuTnneTkWO/nP8QYYAJuujmR1t8H49KUShDKe2WLC', 'koxema3358@fsouda.com', 'romashka', 'lyubka',
        true, 2, '2023-02-01 14:05:33');
INSERT INTO public.epam_user (id, uuid, username, password, email, first_name, last_name, is_activated, role_id,
                              created) OVERRIDING SYSTEM VALUE
VALUES (137, 'afc18f7c-c0df-4cb9-8f23-3ef822eb55c0', 'dgq12',
        '$2a$12$43CWNrtmkjQ9diaB1IeONOp5F8lwVa1G3.fXZ0eXXg6iL.3TvtJ4a', 'dgq12@sdf.sd', 'Ромашка', 'Любишка', true, 2,
        '2023-02-22 23:36:37');
INSERT INTO public.epam_user (id, uuid, username, password, email, first_name, last_name, is_activated, role_id,
                              created) OVERRIDING SYSTEM VALUE
VALUES (107, '3dd74f63-6256-4251-850c-ad8c7c481da2', 'adm',
        '$2a$12$z1ouMoQ3kAfs3/iumM.ek.kaGcAFBUb3hOTuYjItOlZdX29lpo9Ym', 'poznan449@gmail.com', 'adm', 'adm', true, 1,
        '2023-01-19 14:30:29');
INSERT INTO public.epam_user (id, uuid, username, password, email, first_name, last_name, is_activated, role_id,
                              created) OVERRIDING SYSTEM VALUE
VALUES (136, 'a42fc1fa-36dc-4827-b0ce-3c1fa4d9f213', 'romanasdsasd',
        '$2a$12$yxaTargzlL7MALMnfWiMZ.JShAkO6fP7XdAaz1vOVaXSlUJmjI5AW', 'asdfsda@sdf.aaaadsds', 'asdgdsf', 'sdfgdsg',
        false, 2, '2023-02-05 21:07:47');
INSERT INTO public.epam_user (id, uuid, username, password, email, first_name, last_name, is_activated, role_id,
                              created) OVERRIDING SYSTEM VALUE
VALUES (138, '92488595-b455-4045-be85-617d4e0a1ff8', 'rewq',
        '$2a$12$Tq3TTKbIQBG1ezeCBWTSx.uiR24VwfR4pJnzTgjxRVeQmFiZbcKEi', 'rewq@sdf.sdf', 'dfg', 'dfg', true, 2,
        '2023-02-23 17:47:11');
INSERT INTO public.epam_user (id, uuid, username, password, email, first_name, last_name, is_activated, role_id,
                              created) OVERRIDING SYSTEM VALUE
VALUES (135, '7de54158-f8d6-44b0-acae-c659a428ab38', 'romanda',
        '$2a$12$p7lUb7bI2iuKr/zIPcPdTepps4p.d/bSVCUDsMJbkjKaPOUrfWZbq', 'r.s.ko@student.khai.edu', 'Ро', 'Люб', true, 2,
        '2023-02-01 15:08:39');
INSERT INTO public.epam_user (id, uuid, username, password, email, first_name, last_name, is_activated, role_id,
                              created) OVERRIDING SYSTEM VALUE
VALUES (139, '27220e70-b84b-4565-ad33-515e1b2da2a5', 'std1',
        '$2a$12$93r1hZcYzkREkojrrTBe..SzSzZ3gRCWH8LrmJFkX3EG9RXvGUbna', 'std1@sdf.sdf', 'ІРоман', 'УУУіііі', true, 2,
        '2023-02-25 12:50:05');

-- Population the epam_user_test table
INSERT INTO public.epam_user_test (id, uuid, epam_user_id, test_id, is_selected, is_completed, mark_value,
                                   attempt_number, start_time, end_time, created) OVERRIDING SYSTEM VALUE
VALUES (7, 'dc7e842a-ca3d-4af1-95a8-299fa5b15854', 108, 199, true, true, 100, 2, '2023-01-19 22:17:28',
        '2023-01-19 22:18:16', '2023-01-19 14:30:56');
INSERT INTO public.epam_user_test (id, uuid, epam_user_id, test_id, is_selected, is_completed, mark_value,
                                   attempt_number, start_time, end_time, created) OVERRIDING SYSTEM VALUE
VALUES (8, '8ef4c659-42a7-44e5-afb7-c868b03038c8', 109, 199, true, true, 60, 2, '2023-01-19 22:45:04',
        '2023-01-19 22:45:19', '2023-01-20 00:43:05');
INSERT INTO public.epam_user_test (id, uuid, epam_user_id, test_id, is_selected, is_completed, mark_value,
                                   attempt_number, start_time, end_time, created) OVERRIDING SYSTEM VALUE
VALUES (9, '15b0db26-31de-404f-9ad6-f0134607f521', 108, 143, true, true, 0, 1, '2023-01-19 22:57:42',
        '2023-01-19 22:57:49', '2023-01-20 00:57:49');
INSERT INTO public.epam_user_test (id, uuid, epam_user_id, test_id, is_selected, is_completed, mark_value,
                                   attempt_number, start_time, end_time, created) OVERRIDING SYSTEM VALUE
VALUES (10, '6d6d37df-db36-44d6-9e03-5c6ce0b9b73a', 108, 133, true, true, 0, 1, '2023-01-19 23:11:37',
        '2023-01-19 23:11:41', '2023-01-20 01:11:41');
INSERT INTO public.epam_user_test (id, uuid, epam_user_id, test_id, is_selected, is_completed, mark_value,
                                   attempt_number, start_time, end_time, created) OVERRIDING SYSTEM VALUE
VALUES (14, 'ccc4661a-28a7-4f03-bfdf-06e0026ff05b', 110, 199, true, true, 80, 2, '2023-01-20 16:53:23',
        '2023-01-20 16:53:29', '2023-01-20 18:49:09');
INSERT INTO public.epam_user_test (id, uuid, epam_user_id, test_id, is_selected, is_completed, mark_value,
                                   attempt_number, start_time, end_time, created) OVERRIDING SYSTEM VALUE
VALUES (16, 'ad111e5e-9838-41dc-a4f6-ab8cebb5a3da', 110, 18, true, true, 0, 1, '2023-01-20 17:03:45',
        '2023-01-20 17:03:54', '2023-01-20 19:04:28');
INSERT INTO public.epam_user_test (id, uuid, epam_user_id, test_id, is_selected, is_completed, mark_value,
                                   attempt_number, start_time, end_time, created) OVERRIDING SYSTEM VALUE
VALUES (15, '03e1a48d-e81c-4b7b-8cdc-33bf79b90b14', 110, 198, true, true, 37.5, 3, '2023-01-20 17:07:31',
        '2023-01-20 17:07:36', '2023-01-20 18:55:30');
INSERT INTO public.epam_user_test (id, uuid, epam_user_id, test_id, is_selected, is_completed, mark_value,
                                   attempt_number, start_time, end_time, created) OVERRIDING SYSTEM VALUE
VALUES (17, '7d7d055f-d303-48f0-ad88-48b063b6eeae', 110, 176, true, true, 0, 1, '2023-01-20 17:07:44',
        '2023-01-20 17:07:47', '2023-01-20 19:07:58');
INSERT INTO public.epam_user_test (id, uuid, epam_user_id, test_id, is_selected, is_completed, mark_value,
                                   attempt_number, start_time, end_time, created) OVERRIDING SYSTEM VALUE
VALUES (18, 'f137b8ad-9396-44af-b87c-b0d89a4a17aa', 110, 10, true, true, 0, 1, NULL, NULL, '2023-01-20 19:09:10');
INSERT INTO public.epam_user_test (id, uuid, epam_user_id, test_id, is_selected, is_completed, mark_value,
                                   attempt_number, start_time, end_time, created) OVERRIDING SYSTEM VALUE
VALUES (19, 'eaf03bb4-831f-43fe-bc2a-8adc878223f6', 110, 106, true, true, 0, 1, '2023-01-20 19:09:41',
        '2023-01-20 19:09:43', '2023-01-20 19:09:44');
INSERT INTO public.epam_user_test (id, uuid, epam_user_id, test_id, is_selected, is_completed, mark_value,
                                   attempt_number, start_time, end_time, created) OVERRIDING SYSTEM VALUE
VALUES (20, 'bb27279a-319e-4468-b9f0-b47e58c202d9', 110, 160, true, true, 0, 1, '2023-01-20 19:10:15',
        '2023-01-20 19:10:17', '2023-01-20 19:10:50');
INSERT INTO public.epam_user_test (id, uuid, epam_user_id, test_id, is_selected, is_completed, mark_value,
                                   attempt_number, start_time, end_time, created) OVERRIDING SYSTEM VALUE
VALUES (22, 'b6c8622f-1006-47b7-a94d-4324c4db8d37', 108, 202, true, true, 100, 1, '2023-01-27 21:05:12',
        '2023-01-27 21:05:21', '2023-01-27 21:05:22');
INSERT INTO public.epam_user_test (id, uuid, epam_user_id, test_id, is_selected, is_completed, mark_value,
                                   attempt_number, start_time, end_time, created) OVERRIDING SYSTEM VALUE
VALUES (13, '704f52c9-ed80-4552-b62d-775d2a730874', 108, 67, true, true, 100, 1, '2023-01-27 21:29:05',
        '2023-01-27 21:29:24', '2023-01-20 17:32:06');
INSERT INTO public.epam_user_test (id, uuid, epam_user_id, test_id, is_selected, is_completed, mark_value,
                                   attempt_number, start_time, end_time, created) OVERRIDING SYSTEM VALUE
VALUES (25, 'dba229b0-a467-4d64-b1a1-dbd20ea52a29', 108, 203, true, true, 50, 1, '2023-01-30 16:32:15',
        '2023-01-30 16:32:23', '2023-01-30 16:31:56');
INSERT INTO public.epam_user_test (id, uuid, epam_user_id, test_id, is_selected, is_completed, mark_value,
                                   attempt_number, start_time, end_time, created) OVERRIDING SYSTEM VALUE
VALUES (37, '5af4ac79-2daa-4b29-a193-36b238ff0c3f', 139, 203, true, true, 0, 5, '2023-02-26 17:53:36.690177',
        '2023-02-26 17:53:39', '2023-02-26 17:52:39');
INSERT INTO public.epam_user_test (id, uuid, epam_user_id, test_id, is_selected, is_completed, mark_value,
                                   attempt_number, start_time, end_time, created) OVERRIDING SYSTEM VALUE
VALUES (23, '8328ef5d-9d17-46fe-969d-358f7f43d9b0', 106, 202, true, true, 100, 1, '2023-01-27 21:13:06',
        '2023-01-27 21:13:14', '2023-01-27 21:12:58');
INSERT INTO public.epam_user_test (id, uuid, epam_user_id, test_id, is_selected, is_completed, mark_value,
                                   attempt_number, start_time, end_time, created) OVERRIDING SYSTEM VALUE
VALUES (36, '23c74e8b-9121-4570-b4e6-16fd395a9e8d', 139, 208, true, true, 50, 4, '2023-02-26 18:01:51.169699',
        '2023-02-26 18:02:26', '2023-02-26 17:47:35');

-- Changing sequence start number
SELECT pg_catalog.setval('public.answer_id_seq', 322, true);
SELECT pg_catalog.setval('public.epam_user_id_seq', 139, true);
SELECT pg_catalog.setval('public.epam_user_test_id_seq', 37, true);
SELECT pg_catalog.setval('public.question_id_seq', 245, true);
SELECT pg_catalog.setval('public.role_id_seq', 3, true);
SELECT pg_catalog.setval('public.subject_id_seq', 30, true);
SELECT pg_catalog.setval('public.test_id_seq', 208, true);
