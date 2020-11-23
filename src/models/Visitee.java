package models;

import services.*;
public interface Visitee {
    public void accept(Visitor visitor);
}