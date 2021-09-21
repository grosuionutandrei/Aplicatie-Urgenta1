package siit.model;

public enum UsersRole {
    ADMIN("admin"),POMPIER("pompier"),PRIMARIE("primarie");
    private  String rol;
    UsersRole(String state){
       this.rol=state;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
}
