package repositorio;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

public class Repositorio<T>{

    private HashMap<Long,T> mapa = new HashMap<>();
    private AtomicLong idGenerador = new AtomicLong();

    public void guardar(T entidad){
        long id = idGenerador.incrementAndGet();

        try{
            entidad.getClass().getMethod("setId", Long.class).invoke(entidad, id);
        } catch(Exception e){}

        mapa.put(id,entidad);
    }

    public List<T> todosLosRegistros(){
        return new ArrayList<>(mapa.values());
    }

    public Optional<T> buscarPorId(Long id){
        return Optional.ofNullable(mapa.get(id));
    }

    public List<T> buscarPorCampo(String campo, Object valor){
        List<T> resultados = new ArrayList<>();
        try{
            for(T entidad : mapa.values()){
                Method metodoObtenerCampo = entidad.getClass().getMethod("get" + primerLetraMayuscula(campo));
                Object valorEncontrado = metodoObtenerCampo.invoke(entidad);

                if(valorEncontrado != null && valorEncontrado.equals(valor)){
                    resultados.add(entidad);
                }
            }
        } catch(Exception e){
            e.printStackTrace();
        }

        return resultados;
    }

    public Optional<T> actualizarRegistro(Long id, T entidad){
        if(!mapa.containsKey(id)){
            return Optional.empty();
        }

        try{
            Method setIdMethod = entidad.getClass().getMethod("setId",Long.class);
            setIdMethod.invoke(entidad,id);

            mapa.put(id,entidad);
            return Optional.of(entidad);
        } catch(Exception e){
            return Optional.empty();
        }
    }

    public Optional<T> eliminarRegistro(Long id){
        if(!mapa.containsKey(id)){
            return Optional.empty();
        } else{
            return Optional.ofNullable(mapa.remove(id));
        }
    }

    private String primerLetraMayuscula(String campo){
        if(campo == null || campo.isEmpty()){
            return campo;
        }
        return campo.substring(0,1).toUpperCase() + campo.substring(1);
    }
}