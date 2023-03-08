CREATE DATABASE epam_examination_system
    ENCODING = 'UTF8'
    LC_COLLATE = 'Russian_Ukraine.1251'
    LC_CTYPE = 'Russian_Ukraine.1251'
    TABLESPACE = pg_default
    OWNER = postgres;

SET check_function_bodies = false;

-- uuid-ossp extension

-- DROP EXTENSION IF EXISTS "uuid-ossp" CASCADE;
CREATE EXTENSION "uuid-ossp"
    WITH SCHEMA public
    VERSION '1.1';

COMMENT ON EXTENSION "uuid-ossp" IS E'Generator of uuid';

-- -- object: public.uuid_nil | type: FUNCTION --
-- -- DROP FUNCTION IF EXISTS public.uuid_nil() CASCADE;
-- CREATE FUNCTION public.uuid_nil ()
-- 	RETURNS uuid
-- 	LANGUAGE c
-- 	IMMUTABLE 
-- 	STRICT
-- 	SECURITY INVOKER
-- 	PARALLEL SAFE
-- 	COST 1
-- 	AS '$libdir/uuid-ossp', 'uuid_nil';

-- ALTER FUNCTION public.uuid_nil() OWNER TO postgres;

-- 
-- -- object: public.uuid_ns_dns | type: FUNCTION --
-- -- DROP FUNCTION IF EXISTS public.uuid_ns_dns() CASCADE;
-- CREATE FUNCTION public.uuid_ns_dns ()
-- 	RETURNS uuid
-- 	LANGUAGE c
-- 	IMMUTABLE 
-- 	STRICT
-- 	SECURITY INVOKER
-- 	PARALLEL SAFE
-- 	COST 1
-- 	AS '$libdir/uuid-ossp', 'uuid_ns_dns';

-- ALTER FUNCTION public.uuid_ns_dns() OWNER TO postgres;

-- 
-- -- object: public.uuid_ns_url | type: FUNCTION --
-- -- DROP FUNCTION IF EXISTS public.uuid_ns_url() CASCADE;
-- CREATE FUNCTION public.uuid_ns_url ()
-- 	RETURNS uuid
-- 	LANGUAGE c
-- 	IMMUTABLE 
-- 	STRICT
-- 	SECURITY INVOKER
-- 	PARALLEL SAFE
-- 	COST 1
-- 	AS '$libdir/uuid-ossp', 'uuid_ns_url';

-- ALTER FUNCTION public.uuid_ns_url() OWNER TO postgres;

-- 
-- -- object: public.uuid_ns_oid | type: FUNCTION --
-- -- DROP FUNCTION IF EXISTS public.uuid_ns_oid() CASCADE;
-- CREATE FUNCTION public.uuid_ns_oid ()
-- 	RETURNS uuid
-- 	LANGUAGE c
-- 	IMMUTABLE 
-- 	STRICT
-- 	SECURITY INVOKER
-- 	PARALLEL SAFE
-- 	COST 1
-- 	AS '$libdir/uuid-ossp', 'uuid_ns_oid';

-- ALTER FUNCTION public.uuid_ns_oid() OWNER TO postgres;

-- 
-- -- object: public.uuid_ns_x500 | type: FUNCTION --
-- -- DROP FUNCTION IF EXISTS public.uuid_ns_x500() CASCADE;
-- CREATE FUNCTION public.uuid_ns_x500 ()
-- 	RETURNS uuid
-- 	LANGUAGE c
-- 	IMMUTABLE 
-- 	STRICT
-- 	SECURITY INVOKER
-- 	PARALLEL SAFE
-- 	COST 1
-- 	AS '$libdir/uuid-ossp', 'uuid_ns_x500';

-- ALTER FUNCTION public.uuid_ns_x500() OWNER TO postgres;

-- 
-- -- object: public.uuid_generate_v1 | type: FUNCTION --
-- -- DROP FUNCTION IF EXISTS public.uuid_generate_v1() CASCADE;
-- CREATE FUNCTION public.uuid_generate_v1 ()
-- 	RETURNS uuid
-- 	LANGUAGE c
-- 	VOLATILE 
-- 	STRICT
-- 	SECURITY INVOKER
-- 	PARALLEL SAFE
-- 	COST 1
-- 	AS '$libdir/uuid-ossp', 'uuid_generate_v1';

-- ALTER FUNCTION public.uuid_generate_v1() OWNER TO postgres;

-- 
-- -- object: public.uuid_generate_v1mc | type: FUNCTION --
-- -- DROP FUNCTION IF EXISTS public.uuid_generate_v1mc() CASCADE;
-- CREATE FUNCTION public.uuid_generate_v1mc ()
-- 	RETURNS uuid
-- 	LANGUAGE c
-- 	VOLATILE 
-- 	STRICT
-- 	SECURITY INVOKER
-- 	PARALLEL SAFE
-- 	COST 1
-- 	AS '$libdir/uuid-ossp', 'uuid_generate_v1mc';

-- ALTER FUNCTION public.uuid_generate_v1mc() OWNER TO postgres;

-- 
-- -- object: public.uuid_generate_v3 | type: FUNCTION --
-- -- DROP FUNCTION IF EXISTS public.uuid_generate_v3(uuid,text) CASCADE;
-- CREATE FUNCTION public.uuid_generate_v3 (namespace uuid, name text)
-- 	RETURNS uuid
-- 	LANGUAGE c
-- 	IMMUTABLE 
-- 	STRICT
-- 	SECURITY INVOKER
-- 	PARALLEL SAFE
-- 	COST 1
-- 	AS '$libdir/uuid-ossp', 'uuid_generate_v3';

-- ALTER FUNCTION public.uuid_generate_v3(uuid,text) OWNER TO postgres;

-- 
-- -- object: public.uuid_generate_v4 | type: FUNCTION --
-- -- DROP FUNCTION IF EXISTS public.uuid_generate_v4() CASCADE;
-- CREATE FUNCTION public.uuid_generate_v4 ()
-- 	RETURNS uuid
-- 	LANGUAGE c
-- 	VOLATILE 
-- 	STRICT
-- 	SECURITY INVOKER
-- 	PARALLEL SAFE
-- 	COST 1
-- 	AS '$libdir/uuid-ossp', 'uuid_generate_v4';

-- ALTER FUNCTION public.uuid_generate_v4() OWNER TO postgres;

-- 
-- -- object: public.uuid_generate_v5 | type: FUNCTION --
-- -- DROP FUNCTION IF EXISTS public.uuid_generate_v5(uuid,text) CASCADE;
-- CREATE FUNCTION public.uuid_generate_v5 (namespace uuid, name text)
-- 	RETURNS uuid
-- 	LANGUAGE c
-- 	IMMUTABLE 
-- 	STRICT
-- 	SECURITY INVOKER
-- 	PARALLEL SAFE
-- 	COST 1
-- 	AS '$libdir/uuid-ossp', 'uuid_generate_v5';

-- ALTER FUNCTION public.uuid_generate_v5(uuid,text) OWNER TO postgres;

-- 
-- object: public.epam_user | type: TABLE --
-- DROP TABLE IF EXISTS public.epam_user CASCADE;
CREATE TABLE public.epam_user
(
    id           bigint                 NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT BY 1 MINVALUE 1 MAXVALUE 9223372036854775807 START WITH 1 CACHE 1 ),
    uuid         uuid                   NOT NULL DEFAULT uuid_generate_v4(),
    username     character varying(50)  NOT NULL,
    password     character varying(255) NOT NULL,
    email        character varying(255),
    first_name   character varying(40),
    last_name    character varying(50),
    is_activated boolean                         DEFAULT false,
    role_id      bigint                 NOT NULL,
    created      timestamp              NOT NULL DEFAULT (now())::timestamp(0) without time zone,
    CONSTRAINT epam_user_pk PRIMARY KEY (id),
    CONSTRAINT epam_user_uuid_uq UNIQUE (uuid),
    CONSTRAINT epam_user_username_uq UNIQUE (username),
    CONSTRAINT epam_user_email_uq UNIQUE (email)
);

ALTER TABLE public.epam_user
    OWNER TO postgres;


-- -- object: public.epam_user_id_seq | type: SEQUENCE --
-- -- DROP SEQUENCE IF EXISTS public.epam_user_id_seq CASCADE;
-- CREATE SEQUENCE public.epam_user_id_seq
-- 	INCREMENT BY 1
-- 	MINVALUE 1
-- 	MAXVALUE 9223372036854775807
-- 	START WITH 1
-- 	CACHE 1
-- 	NO CYCLE
-- 	OWNED BY NONE;
--

-- ALTER SEQUENCE public.epam_user_id_seq OWNER TO postgres;

--
-- object: public.role | type: TABLE --
-- DROP TABLE IF EXISTS public.role CASCADE;
CREATE TABLE public.role
(
    id      bigint                NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT BY 1 MINVALUE 1 MAXVALUE 9223372036854775807 START WITH 1 CACHE 1 ),
    uuid    uuid                  NOT NULL DEFAULT uuid_generate_v4(),
    name    character varying(50) NOT NULL,
    created timestamp             NOT NULL DEFAULT (now())::timestamp(0) without time zone,
    CONSTRAINT role_name_ck CHECK (((name)::text = ANY
                                    ((ARRAY ['admin'::character varying, 'student'::character varying, 'tutor'::character varying])::text[]))),
    CONSTRAINT role_pk PRIMARY KEY (id),
    CONSTRAINT role_uuid_uq UNIQUE (uuid)
);

ALTER TABLE public.role
    OWNER TO postgres;


-- -- object: public.role_id_seq | type: SEQUENCE --
-- -- DROP SEQUENCE IF EXISTS public.role_id_seq CASCADE;
-- CREATE SEQUENCE public.role_id_seq
-- 	INCREMENT BY 1
-- 	MINVALUE 1
-- 	MAXVALUE 9223372036854775807
-- 	START WITH 1
-- 	CACHE 1
-- 	NO CYCLE
-- 	OWNED BY NONE;
--

-- ALTER SEQUENCE public.role_id_seq OWNER TO postgres;

--
-- object: public.test | type: TABLE --
-- DROP TABLE IF EXISTS public.test CASCADE;
CREATE TABLE public.test
(
    id                   bigint                 NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT BY 1 MINVALUE 1 MAXVALUE 9223372036854775807 START WITH 1 CACHE 1 ),
    uuid                 uuid                   NOT NULL DEFAULT uuid_generate_v4(),
    name                 character varying(255) NOT NULL,
    description          character varying(500),
    complexity           character varying(50)  NOT NULL DEFAULT 'easy',
    duration             integer                NOT NULL,
    total_attempt_number integer                         DEFAULT 0,
    subject_id           bigint,
    expiration_date      timestamp,
    max_attempt_number   integer                         DEFAULT 1,
    created              timestamp              NOT NULL DEFAULT (now())::timestamp(0) without time zone,
    CONSTRAINT test_complexity_ck CHECK (((complexity)::text = ANY
                                          ((ARRAY ['easy'::character varying, 'moderate'::character varying, 'hard'::character varying])::text[]))),
    CONSTRAINT test_pk PRIMARY KEY (id),
    CONSTRAINT test_uuid_uq UNIQUE (uuid)
);

ALTER TABLE public.test
    OWNER TO postgres;


-- -- object: public.test_id_seq | type: SEQUENCE --
-- -- DROP SEQUENCE IF EXISTS public.test_id_seq CASCADE;
-- CREATE SEQUENCE public.test_id_seq
-- 	INCREMENT BY 1
-- 	MINVALUE 1
-- 	MAXVALUE 9223372036854775807
-- 	START WITH 1
-- 	CACHE 1
-- 	NO CYCLE
-- 	OWNED BY NONE;
--

-- ALTER SEQUENCE public.test_id_seq OWNER TO postgres;

--
-- object: public.question | type: TABLE --
-- DROP TABLE IF EXISTS public.question CASCADE;
CREATE TABLE public.question
(
    id          bigint                 NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT BY 1 MINVALUE 1 MAXVALUE 9223372036854775807 START WITH 1 CACHE 1 ),
    uuid        uuid                   NOT NULL DEFAULT uuid_generate_v4(),
    type        character varying(100) NOT NULL,
    content     character varying(500) NOT NULL,
    description character varying(500),
    test_id     bigint,
    created     timestamp              NOT NULL DEFAULT (now())::timestamp(0) without time zone,
    CONSTRAINT question_type_ck CHECK (((type)::text = ANY
                                        ((ARRAY ['multiple_choice'::character varying, 'single_choice'::character varying, 'true_false'::character varying, 'numerical'::character varying, 'text'::character varying])::text[]))),
    CONSTRAINT question_pk PRIMARY KEY (id),
    CONSTRAINT question_uuid_uq UNIQUE (uuid)
);

ALTER TABLE public.question
    OWNER TO postgres;


-- -- object: public.question_id_seq | type: SEQUENCE --
-- -- DROP SEQUENCE IF EXISTS public.question_id_seq CASCADE;
-- CREATE SEQUENCE public.question_id_seq
-- 	INCREMENT BY 1
-- 	MINVALUE 1
-- 	MAXVALUE 9223372036854775807
-- 	START WITH 1
-- 	CACHE 1
-- 	NO CYCLE
-- 	OWNED BY NONE;
--

-- ALTER SEQUENCE public.question_id_seq OWNER TO postgres;

--
-- object: public.answer | type: TABLE --
-- DROP TABLE IF EXISTS public.answer CASCADE;
CREATE TABLE public.answer
(
    id          bigint                  NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT BY 1 MINVALUE 1 MAXVALUE 9223372036854775807 START WITH 1 CACHE 1 ),
    uuid        uuid                    NOT NULL DEFAULT uuid_generate_v4(),
    content     character varying(2040) NOT NULL,
    is_correct  boolean                 NOT NULL,
    question_id bigint,
    created     timestamp               NOT NULL DEFAULT (now())::timestamp(0) without time zone,
    CONSTRAINT answer_pk PRIMARY KEY (id),
    CONSTRAINT answer_uuid_uq UNIQUE (uuid)
);

ALTER TABLE public.answer
    OWNER TO postgres;


-- -- object: public.answer_id_seq | type: SEQUENCE --
-- -- DROP SEQUENCE IF EXISTS public.answer_id_seq CASCADE;
-- CREATE SEQUENCE public.answer_id_seq
-- 	INCREMENT BY 1
-- 	MINVALUE 1
-- 	MAXVALUE 9223372036854775807
-- 	START WITH 1
-- 	CACHE 1
-- 	NO CYCLE
-- 	OWNED BY NONE;
--

-- ALTER SEQUENCE public.answer_id_seq OWNER TO postgres;

--
-- object: public.subject | type: TABLE --
-- DROP TABLE IF EXISTS public.subject CASCADE;
CREATE TABLE public.subject
(
    id          bigint                 NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT BY 1 MINVALUE 1 MAXVALUE 9223372036854775807 START WITH 1 CACHE 1 ),
    uuid        uuid                   NOT NULL DEFAULT uuid_generate_v4(),
    name        character varying(255) NOT NULL,
    description character varying(2000),
    created     timestamp              NOT NULL DEFAULT (now())::timestamp(0) without time zone,
    CONSTRAINT subject_pk PRIMARY KEY (id),
    CONSTRAINT subject_uuid_uq UNIQUE (uuid),
    CONSTRAINT subject_name_uq UNIQUE (name)
);

ALTER TABLE public.subject
    OWNER TO postgres;


-- -- object: public.subject_id_seq | type: SEQUENCE --
-- -- DROP SEQUENCE IF EXISTS public.subject_id_seq CASCADE;
-- CREATE SEQUENCE public.subject_id_seq
-- 	INCREMENT BY 1
-- 	MINVALUE 1
-- 	MAXVALUE 9223372036854775807
-- 	START WITH 1
-- 	CACHE 1
-- 	NO CYCLE
-- 	OWNED BY NONE;
--

-- ALTER SEQUENCE public.subject_id_seq OWNER TO postgres;

--
-- object: public.epam_user_test | type: TABLE --
-- DROP TABLE IF EXISTS public.epam_user_test CASCADE;
CREATE TABLE public.epam_user_test
(
    id             bigint    NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT BY 1 MINVALUE 1 MAXVALUE 9223372036854775807 START WITH 1 CACHE 1 ),
    uuid           uuid      NOT NULL DEFAULT uuid_generate_v4(),
    epam_user_id   bigint,
    test_id        bigint,
    is_selected    boolean            DEFAULT false,
    is_completed   boolean            DEFAULT false,
    mark_value     real,
    attempt_number integer            DEFAULT 0,
    start_time     timestamp,
    end_time       timestamp,
    created        timestamp NOT NULL DEFAULT (now())::timestamp(0) without time zone,
    CONSTRAINT epam_user_test_pk PRIMARY KEY (id)
);

ALTER TABLE public.epam_user_test
    OWNER TO postgres;


-- -- object: public.epam_user_test_id_seq | type: SEQUENCE --
-- -- DROP SEQUENCE IF EXISTS public.epam_user_test_id_seq CASCADE;
-- CREATE SEQUENCE public.epam_user_test_id_seq
-- 	INCREMENT BY 1
-- 	MINVALUE 1
-- 	MAXVALUE 9223372036854775807
-- 	START WITH 1
-- 	CACHE 1
-- 	NO CYCLE
-- 	OWNED BY NONE;
--

-- ALTER SEQUENCE public.epam_user_test_id_seq OWNER TO postgres;

--
-- object: role_fk | type: CONSTRAINT --
-- ALTER TABLE public.epam_user DROP CONSTRAINT IF EXISTS role_fk CASCADE;
ALTER TABLE public.epam_user
    ADD CONSTRAINT role_fk FOREIGN KEY (role_id)
        REFERENCES public.role (id) MATCH SIMPLE
        ON DELETE NO ACTION ON UPDATE NO ACTION;


-- object: subject_fk | type: CONSTRAINT --
-- ALTER TABLE public.test DROP CONSTRAINT IF EXISTS subject_fk CASCADE;
ALTER TABLE public.test
    ADD CONSTRAINT subject_fk FOREIGN KEY (subject_id)
        REFERENCES public.subject (id) MATCH FULL
        ON DELETE SET NULL ON UPDATE CASCADE;


-- object: test_fk | type: CONSTRAINT --
-- ALTER TABLE public.question DROP CONSTRAINT IF EXISTS test_fk CASCADE;
ALTER TABLE public.question
    ADD CONSTRAINT test_fk FOREIGN KEY (test_id)
        REFERENCES public.test (id) MATCH SIMPLE
        ON DELETE CASCADE ON UPDATE CASCADE;


-- object: question_fk | type: CONSTRAINT --
-- ALTER TABLE public.answer DROP CONSTRAINT IF EXISTS question_fk CASCADE;
ALTER TABLE public.answer
    ADD CONSTRAINT question_fk FOREIGN KEY (question_id)
        REFERENCES public.question (id) MATCH SIMPLE
        ON DELETE CASCADE ON UPDATE CASCADE;


-- object: epam_user_fk | type: CONSTRAINT --
-- ALTER TABLE public.epam_user_test DROP CONSTRAINT IF EXISTS epam_user_fk CASCADE;
ALTER TABLE public.epam_user_test
    ADD CONSTRAINT epam_user_fk FOREIGN KEY (epam_user_id)
        REFERENCES public.epam_user (id) MATCH SIMPLE
        ON DELETE SET NULL ON UPDATE CASCADE;


-- object: test_fk | type: CONSTRAINT --
-- ALTER TABLE public.epam_user_test DROP CONSTRAINT IF EXISTS test_fk CASCADE;
ALTER TABLE public.epam_user_test
    ADD CONSTRAINT test_fk FOREIGN KEY (test_id)
        REFERENCES public.test (id) MATCH SIMPLE
        ON DELETE SET NULL ON UPDATE CASCADE;


-- object: "grant_CU_eb94f049ac" | type: PERMISSION --
GRANT CREATE, USAGE
    ON SCHEMA public
    TO postgres;


-- object: "grant_CU_cd8e46e7b6" | type: PERMISSION --
GRANT CREATE, USAGE
    ON SCHEMA public
    TO PUBLIC;

