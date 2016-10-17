package com.adidas.kata.kata1;

class Role implements IPlayer {
    private final int role;
    private final int[] whomIKill;
    private boolean isAlive = true;

    Role(int[] whomIKill, int role) {
        this.whomIKill = whomIKill;
        this.role = role;
    }

    @Override
    public void try2Kill(IPlayer role) {
        if( killable( role.getRole() ) ) {
            System.out.println(" " + role + " killed by " + getRole() );
            role.kill();
        }
    }

    private boolean killable(int role) {
        for(int killableRole : whomIKill ) {
            if( killableRole == role) {
                return true;
            }
        }

        return false;
    }

    @Override
    public void kill() {
        System.out.println(role + " is dead ");
        isAlive = false;
    }

    @Override
    public boolean isAlive() {
        return isAlive;
    }

    @Override
    public int getRole() {
        return role;
    }

    @Override
    public String toString() {
        return "" + role;
    }
}
