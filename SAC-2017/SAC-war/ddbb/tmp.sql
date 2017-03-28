delete from calificacion;

select fn_savecalificacion(null, 1, 5, '2012-09-30', 10);
select fn_savecalificacion(null, 1, 5, '2012-10-03', 10);
select fn_savecalificacion(null, 3, 5, '2012-10-01', 9);
select fn_savecalificacion(null, 3, 5, '2012-10-04', 10);

select * from v_calificacion;

