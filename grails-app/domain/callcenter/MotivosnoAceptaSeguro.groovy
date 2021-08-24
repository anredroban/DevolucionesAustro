package callcenter

class MotivosnoAceptaSeguro {
    String nombre
    String nombreSubSubEstado
    boolean isActive

    static constraints = {
        nombre nullable: true
        nombreSubSubEstado nullable: true
    }

    static mapping = {
        version false
    }
}
