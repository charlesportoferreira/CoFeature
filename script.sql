select nome, sum(if(nomeclasseorigem = '041',1,0)) as '041',sum(if(nomeclasseorigem = '111',1,0)) as '111',sum(if(nomeclasseorigem = '185',1,0)) as '185',sum(if(nomeclasseorigem = '211',1,0)) as '211',sum(if(nomeclasseorigem = '344',1,0)) as '344',sum(if(nomeclasseorigem = '388',1,0)) as '388',sum(if(nomeclasseorigem = '612',1,0)) as '612' from feature group by nome;