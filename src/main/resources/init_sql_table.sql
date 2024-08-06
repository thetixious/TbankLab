CREATE TABLE translation_request(
    id bigserial not null,
    ip_address varchar(255),
    translation_text varchar(255),
    result_text varchar(255),
    primary key (id)
)