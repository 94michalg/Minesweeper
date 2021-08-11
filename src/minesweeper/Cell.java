package minesweeper;

public class Cell {
    private boolean bomb = false;
    private boolean flag = false;
    private boolean checked = false;
    private int minesAround;

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public boolean isBomb() {
        return bomb;
    }

    public void setBomb(boolean bomb) {
        this.bomb = bomb;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public void setMinesAround(int minesAround) {
        this.minesAround = minesAround;
    }

    public int getMinesAround() {
        return minesAround;
    }


    // "." - unexplored cell, "/" - checked cell without mines around
    // "*" - unchecked and flagged cell, numbers "1-8" - checked cell with mines around
    @Override
    public String toString() {
        if (this.isChecked() && this.getMinesAround() == 0) {
            return "/";
        } else if (this.isChecked() && this.getMinesAround() != 0) {
            return "" + this.getMinesAround();
        } else if (this.isFlag()) {
            return "*";
        } else {
            return ".";
        }
    }
}
