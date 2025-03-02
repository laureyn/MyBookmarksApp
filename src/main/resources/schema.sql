BEGIN;

ALTER TABLE IF EXISTS public.bookmark	DROP CONSTRAINT IF EXISTS fk_bookmark_category;
ALTER TABLE IF EXISTS public.link 		DROP CONSTRAINT IF EXISTS fk_link_bookmark;
ALTER TABLE IF EXISTS public.link_tag	DROP CONSTRAINT IF EXISTS fk_link_tag_link;
ALTER TABLE IF EXISTS public.link_tag	DROP CONSTRAINT IF EXISTS fk_link_tag_tag;

DROP TABLE IF EXISTS public.bookmark;
DROP TABLE IF EXISTS public.category;
DROP TABLE IF EXISTS public.link;
DROP TABLE IF EXISTS public.tag;
DROP TABLE IF EXISTS public.link_tag;

CREATE TABLE IF NOT EXISTS public.bookmark (
	id				uuid		NOT NULL,
	name			VARCHAR(50)	NOT NULL,
	description		VARCHAR,
	category_id		integer,
	image_url		VARCHAR,
	image_alt_text	VARCHAR,
	CONSTRAINT bookmark_pkey PRIMARY KEY (id)
	);


CREATE TABLE IF NOT EXISTS public.category (
	id				integer		NOT NULL,
	name			VARCHAR(20)	NOT NULL,
	list_position	smallint	NOT NULL,
	image_url		VARCHAR,
	CONSTRAINT category_pkey	PRIMARY KEY (id),
	CONSTRAINT unique_name		UNIQUE (name),
	CONSTRAINT unique_order		UNIQUE (list_position)
	);


CREATE TABLE IF NOT EXISTS public.link (
	bookmark_id	uuid	NOT NULL,
	name		VARCHAR	NOT NULL,
	url			VARCHAR	NOT NULL,
	CONSTRAINT link_pk PRIMARY KEY (bookmark_id, name)
	);


CREATE TABLE IF NOT EXISTS public.tag (
	name	VARCHAR(20)	NOT NULL,
	color	VARCHAR(20)	NOT NULL,
	CONSTRAINT pk_tag PRIMARY KEY (name)
	);

CREATE TABLE IF NOT EXISTS public.link_tag (
	link_bookmark_id	uuid		NOT NULL,
	link_name			VARCHAR		NOT NULL,
	tag_name			VARCHAR(20)	NOT NULL,
	CONSTRAINT pk_link_tag PRIMARY KEY (link_bookmark_id, link_name, tag_name)
	);

ALTER TABLE IF EXISTS public.bookmark
	ADD CONSTRAINT fk_bookmark_category FOREIGN KEY (category_id)
		REFERENCES public.category (id)
			ON UPDATE CASCADE
			ON DELETE SET NULL;


ALTER TABLE IF EXISTS public.link
	ADD CONSTRAINT fk_link_bookmark FOREIGN KEY (bookmark_id)
		REFERENCES public.bookmark (id)
			ON UPDATE CASCADE
			ON DELETE CASCADE;


ALTER TABLE IF EXISTS public.link_tag
	ADD CONSTRAINT fk_link_tag_link FOREIGN KEY (link_bookmark_id, link_name)
		REFERENCES public.link (bookmark_id, name)
			ON UPDATE CASCADE
			ON DELETE CASCADE;


ALTER TABLE IF EXISTS public.link_tag
	ADD CONSTRAINT fk_link_tag_tag FOREIGN KEY (tag_name)
		REFERENCES public.tag (name)
			ON UPDATE CASCADE
			ON DELETE CASCADE;

END;
