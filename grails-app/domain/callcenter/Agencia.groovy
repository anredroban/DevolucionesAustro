package callcenter

class Agencia {
    String nombre
    Provincia provincia
    String direccionAgencia
    String asesorAgencia
    String tipoPersonaAgencia
    String correoPersonaAgencia
    boolean isActive
    static constraints = {
        nombre nullable: true
        direccionAgencia nullable: true
        asesorAgencia nullable: true
        tipoPersonaAgencia nullable: true
        correoPersonaAgencia nullable: true
        isActive nullable: true
    }
    static mapping = {
        version false
    }
}
