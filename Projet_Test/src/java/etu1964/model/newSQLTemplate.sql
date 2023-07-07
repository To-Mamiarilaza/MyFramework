/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/SQLTemplate.sql to edit this template
 */
/**
 * Author:  to
 * Created: 21 mars 2023
 */

CREATE DATABASE Grocery;

CREATE TABLE legume (
    idLegume SERIAL PRIMARY KEY,
    nom VARCHAR(30),
    prix DECIMAL(7, 2)
);

INSERT INTO legume VALUES 
(DEFAULT, 'Anana', '500'),
(DEFAULT, 'Voatabia', '1000'),
(DEFAULT, 'Tongolo', '800'),
(DEFAULT, 'Voatavo', '2000'),
(DEFAULT, 'Haricot vert', '700'),
(DEFAULT, 'Celerie', '300');