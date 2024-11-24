import entidades.*;
import repositorio.Repositorio;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {

        //Generamos un repositorio para las empresas
        Repositorio<Empresa> repoEmpresas = new Repositorio<>();

        //Instanciamos los objetos de cada clase con sus debidas relaciones
        Pais argentina = Pais.builder()
                .nombre("Argentina")
                .build();

        Provincia buenosAires = Provincia.builder()
                .nombre("Buenos Aires")
                .pais(argentina)
                .build();

        Localidad caba = Localidad.builder()
                .nombre("Caba")
                .provincia(buenosAires)
                .build();

        Domicilio domicilio1 = Domicilio.builder()
                .calle("San Martín")
                .numero(1350)
                .codigoPostal(700)
                .localidad(caba)
                .build();

        Localidad laPlata = Localidad.builder()
                .nombre("La Plata")
                .provincia(buenosAires)
                .build();

        Domicilio domicilio2 = Domicilio.builder()
                .calle("Corrientes")
                .numero(890)
                .codigoPostal(415)
                .localidad(laPlata)
                .build();

        Provincia cordoba = Provincia.builder()
                .nombre("Córdoba")
                .pais(argentina)
                .build();

        Localidad cordobaCapital = Localidad.builder()
                .nombre("Córdoba Capital")
                .provincia(cordoba)
                .build();

        Domicilio domicilio3 = Domicilio.builder()
                .calle("Gutierrez")
                .numero(278)
                .codigoPostal(1080)
                .localidad(cordobaCapital)
                .build();

        Localidad villaCarlosPaz = Localidad.builder()
                .nombre("Villa Carlos Paz")
                .provincia(cordoba)
                .build();

        Domicilio domicilio4 = Domicilio.builder()
                .calle("Moreno")
                .numero(533)
                .codigoPostal(906)
                .localidad(villaCarlosPaz)
                .build();

        Sucursal sucursal1 = Sucursal.builder()
                .nombre("Empresa1 Sucursal1")
                .horarioApertura(LocalTime.of(9,30))
                .horarioCierre(LocalTime.of(20,00))
                .esCasaMatriz(true)
                .domicilio(domicilio1)
                .build();

        Sucursal sucursal2 = Sucursal.builder()
                .nombre("Empresa1 Sucursal2")
                .horarioApertura(LocalTime.of(10,15))
                .horarioCierre(LocalTime.of(19,30))
                .esCasaMatriz(false)
                .domicilio(domicilio2)
                .build();

        Sucursal sucursal3 = Sucursal.builder()
                .nombre("Empresa2 Sucursal1")
                .horarioApertura(LocalTime.of(7,45))
                .horarioCierre(LocalTime.of(22,00))
                .esCasaMatriz(true)
                .domicilio(domicilio3)
                .build();

        Sucursal sucursal4 = Sucursal.builder()
                .nombre("Empresa2 Sucursal2")
                .horarioApertura(LocalTime.of(8,00))
                .horarioCierre(LocalTime.of(21,15))
                .esCasaMatriz(false)
                .domicilio(domicilio4)
                .build();

        Empresa empresa1 = Empresa.builder()
                .nombre("Transporte SA")
                .razonSocial("Razón social (Empresa1)")
                .cuit(64737801)
                .build();
        empresa1.getSucursales().add(sucursal1);
        empresa1.getSucursales().add(sucursal2);

        Empresa empresa2 = Empresa.builder()
                .nombre("Alimentos SRL")
                .razonSocial("Razón Social (Empresa2)")
                .cuit(11946650)
                .build();
        empresa2.getSucursales().add(sucursal3);
        empresa2.getSucursales().add(sucursal4);

        argentina.getProvincias().add(buenosAires);
        argentina.getProvincias().add(cordoba);

        buenosAires.getLocalidades().add(caba);
        buenosAires.getLocalidades().add(laPlata);

        cordoba.getLocalidades().add(cordobaCapital);
        cordoba.getLocalidades().add(villaCarlosPaz);

        sucursal1.setEmpresa(empresa1);
        sucursal2.setEmpresa(empresa1);
        sucursal3.setEmpresa(empresa2);
        sucursal4.setEmpresa(empresa2);

        //Guardamos las empresas en el repositorio
        repoEmpresas.guardar(empresa1);
        repoEmpresas.guardar(empresa2);

        //Mostramos por pantalla todas las empresas
        System.out.println("\n---------- EMPRESAS ---------");
        List<Empresa> empresas = repoEmpresas.todosLosRegistros();
        empresas.forEach(System.out::println);

        //Buscamos una empresa por su ID
        System.out.println("\n---------- BUSCAMOS UNA EMPRESA POR SU ID ---------");
        Optional<Empresa> empresaEncontrada = repoEmpresas.buscarPorId(1L);
        empresaEncontrada.ifPresent(System.out::println);

        //Buscamos una empresa por su nombre
        System.out.println("\n---------- BUSCAMOS UNA EMPRESA POR SU NOMBRE ---------");
        List<Empresa> resultados = repoEmpresas.buscarPorCampo("nombre","Alimentos SRL");
        resultados.forEach(System.out::println);

        //Modificar el cuil de una empresa
        System.out.println("\n---------- MODIFICAMOS EL CUIT DE UNA EMPRESA ---------");
        Empresa empresa1Nueva = Empresa.builder()
                .nombre("Transporte SA")
                .razonSocial("Razón social (Empresa1)")
                .cuit(89420232)
                .build();
        Optional<Empresa> empresaActualizada = repoEmpresas.actualizarRegistro(1L, empresa1Nueva);
        empresaActualizada.ifPresent(System.out::println);

        //Eliminamos una empresa
        System.out.println("\n---------- ELIMINAMOS UNA EMPRESA ---------");
        Optional<Empresa> empresaEliminada = repoEmpresas.eliminarRegistro(2L);
        empresaEliminada.ifPresent(System.out::println);

        //Verificamos que la empresa haya sido eliminada correctamente
        Optional<Empresa> verificarEliminacion = repoEmpresas.buscarPorId(2L);
        if(verificarEliminacion.isEmpty()) {
            System.out.println("La empresa con ID=2 ha sido eliminada.");
        } else{
            System.out.println("La empresa con ID=2 todavía existe.");
        }

    }
}

