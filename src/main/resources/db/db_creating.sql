CREATE TABLE field_names
(
    id serial NOT NULL,
name text COLLATE pg_catalog."default" NOT NULL,
CONSTRAINT field_names_pkey PRIMARY KEY (id),
CONSTRAINT field_names_name_key UNIQUE (name)
);
CREATE TABLE types
(
    id serial NOT NULL,
name character varying(255) COLLATE pg_catalog."default" NOT NULL,
CONSTRAINT types_pkey PRIMARY KEY (id),
CONSTRAINT uk_17go525ou3scbmd4pcftq130f UNIQUE (name)
);
CREATE TABLE fields
(
    id serial NOT NULL,
    active boolean NOT NULL,
label character varying(255) COLLATE pg_catalog."default" NOT NULL,
option character varying(255) COLLATE pg_catalog."default",
required boolean NOT NULL,
type_id integer NOT NULL,
CONSTRAINT fields_pkey PRIMARY KEY (id),
CONSTRAINT uk_c86pmwxbm019esu8vsvjygmeu UNIQUE (label),
CONSTRAINT fkl1po4jofgtdjdp682hob3gfrh FOREIGN KEY (type_id)
REFERENCES public.types (id) MATCH SIMPLE
	ON UPDATE NO ACTION
	ON DELETE NO ACTION
);
CREATE TABLE questionnaire
(
    id serial NOT NULL,
name_id integer NOT NULL,
value text COLLATE pg_catalog."default" NOT NULL,
CONSTRAINT questionnaire_pkey PRIMARY KEY (id),
CONSTRAINT questionnaire_name_id_fkey FOREIGN KEY (name_id)
REFERENCES public.field_names (id) MATCH SIMPLE
	ON UPDATE NO ACTION
	ON DELETE NO ACTION
);
CREATE TABLE responses
(
    id serial NOT NULL,
CONSTRAINT unique_questionnaire_pkey PRIMARY KEY (id)
);
CREATE TABLE questionnaire_response
(
    response_id integer NOT NULL,
questionnaire_id integer NOT NULL,
CONSTRAINT questionnaire_response_pkey PRIMARY KEY (response_id, questionnaire_id),
CONSTRAINT uk_av86ty8c9hl93na3jw2fvwyet UNIQUE (questionnaire_id),
CONSTRAINT questionnaire_response_questionnaire_id_fkey FOREIGN KEY (questionnaire_id)
REFERENCES public.questionnaire (id) MATCH SIMPLE
	ON UPDATE NO ACTION
	ON DELETE NO ACTION,
CONSTRAINT questionnaire_response_response_id_fkey FOREIGN KEY (response_id)
REFERENCES public.responses (id) MATCH SIMPLE
	ON UPDATE NO ACTION
	ON DELETE NO ACTION
);
CREATE TABLE users
(
    id serial NOT NULL,
email character varying(255) COLLATE pg_catalog."default" NOT NULL,
hash character varying(32) COLLATE pg_catalog."default" NOT NULL,
salt character varying(32) COLLATE pg_catalog."default" NOT NULL,
first_name character varying(30) COLLATE pg_catalog."default",
last_name character varying(30) COLLATE pg_catalog."default",
phone character varying(16) COLLATE pg_catalog."default",
CONSTRAINT users_pkey PRIMARY KEY (id),
CONSTRAINT users_email_key UNIQUE (email)
);
CREATE TABLE verification_token
(
    id integer NOT NULL,
token text COLLATE pg_catalog."default" NOT NULL,
created_date date NOT NULL,
expiry_date date NOT NULL,
CONSTRAINT verification_token_pkey PRIMARY KEY (id),
CONSTRAINT verification_token_token_key UNIQUE (token),
CONSTRAINT verification_token_id_fkey FOREIGN KEY (id)
REFERENCES public.users (id) MATCH SIMPLE
	ON UPDATE NO ACTION
	ON DELETE NO ACTION
);

CREATE ROLE qp_admin WITH LOGIN PASSWORD '2IFg%oziprPP$xlX';
GRANT CONNECT ON DATABASE questionnaire_portal TO qp_admin;
GRANT USAGE, SELECT ON ALL SEQUENCES IN SCHEMA public to qp_admin;
GRANT SELECT, INSERT, UPDATE, DELETE ON ALL TABLES IN SCHEMA public TO qp_admin;