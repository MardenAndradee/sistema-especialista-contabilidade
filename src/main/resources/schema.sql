CREATE TABLE if NOT EXISTS tb_perguntas (
    id long AUTO_INCREMENT PRIMARY KEY,
    pergunta varchar(255) not null
);

CREATE TABLE if NOT EXISTS tb_respostas (
    id long AUTO_INCREMENT PRIMARY KEY,
    pergunta_id long,
    resposta varchar(255)
);
