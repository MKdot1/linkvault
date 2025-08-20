-- Created by Vertabelo (http://vertabelo.com)
-- Last modification date: 2025-08-20 14:01:27.607

-- tables
-- Table: category
CREATE TABLE category (
                          id int GENERATED ALWAYS AS IDENTITY (START WITH 1) NOT NULL,
                          category_name varchar(20)  NULL,
                          CONSTRAINT category_uk UNIQUE (category_name),
                          CONSTRAINT category_pk PRIMARY KEY (id)
);

-- Table: link
CREATE TABLE link (
                      id int GENERATED ALWAYS AS IDENTITY (START WITH 1) NOT NULL,
                      address varchar(200)  NULL,
                      description varchar(200)  NOT NULL,
                      created_at timestamp  NULL,
                      workflow_status varchar(20)  NULL CONSTRAINT chk_link_workflow_status CHECK (workflow_status IN ('waiting','in_progress','done','archived','junk')),
                      visibility varchar(20)  NULL CONSTRAINT chk_link_visibility CHECK (visibility IN ('private','public','hidden')),
                      category_id int  NOT NULL,
                      CONSTRAINT link_pk PRIMARY KEY (id)
);

ALTER TABLE link ALTER COLUMN workflow_status SET DEFAULT 'waiting';
ALTER TABLE link ALTER COLUMN visibility      SET DEFAULT 'private';;

-- Table: link_tag
CREATE TABLE link_tag (
                          id int GENERATED ALWAYS AS IDENTITY (START WITH 1) NOT NULL,
                          tag_id int  NOT NULL,
                          link_id int  NOT NULL,
                          CONSTRAINT link_tag_uk UNIQUE (tag_id, link_id),
                          CONSTRAINT link_tag_pk PRIMARY KEY (id)
);

-- Table: tag
CREATE TABLE tag (
                     id int GENERATED ALWAYS AS IDENTITY (START WITH 1) NOT NULL,
                     tag_name varchar(20)  NULL,
                     CONSTRAINT tag_uk UNIQUE (tag_name),
                     CONSTRAINT tag_pk PRIMARY KEY (id)
);

-- foreign keys
-- Reference: link_category (table: link)
ALTER TABLE link ADD CONSTRAINT link_category
    FOREIGN KEY (category_id)
        REFERENCES category (id);

-- Reference: link_tag_link (table: link_tag)
ALTER TABLE link_tag ADD CONSTRAINT link_tag_link
    FOREIGN KEY (link_id)
        REFERENCES link (id);

-- Reference: link_tag_tag (table: link_tag)
ALTER TABLE link_tag ADD CONSTRAINT link_tag_tag
    FOREIGN KEY (tag_id)
        REFERENCES tag (id);

-- End of file.

