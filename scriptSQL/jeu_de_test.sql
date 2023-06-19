insert into UTILISATEUR(idUt,pseudoUt,emailUT,mdpUt,activeUt,idRole) values (1001,"Gael","gael@vae.com","rugby","O",2),
                                                                            (1002,"Jordan","jordan@vae.com","kubikcube","O",2);
                                                                            (1003,"Tom","tom@vae.com","woodparty","0",2);

insert into OBJET(idOb,nomOb,descriptionOb,idCat,idUt) values (501,'Tondeuse comme neuve','Lorem ipsum dolor sit amet, consectetur adipiscing elit.
Donec facilisis, ligula vel posuere cursus, sapien leo dictum nisi, interdum aliquam sem nisi ac purus.',4,1001);
insert into VENTE(idVe,prixBase,prixMin,debutVe,finVe,idSt,idOb) values (450,91,4,STR_TO_DATE('19/06/2023:10:00:00','%d/%m/%Y:%h:%i:%s'),DATE_ADD(STR_TO_DATE('26/06/2023:10:00:00','%d/%m/%Y:%h:%i:%s'), INTERVAL 2 DAY),2,501);

insert into ENCHERIR(idUT,idVe,dateheure,montant) values (1002,450,date_add(STR_TO_DATE('27/2/2022:10:00:00','%d/%m/%Y:%h:%i:%s'),interval 1000 minute),15),
                                                         (1003,450,date_add(STR_TO_DATE('27/2/2022:10:00:00','%d/%m/%Y:%h:%i:%s'),interval 1475 minute),25),
                                                         (1002,450,date_add(STR_TO_DATE('27/2/2022:10:00:00','%d/%m/%Y:%h:%i:%s'),interval 2000 minute),30);
