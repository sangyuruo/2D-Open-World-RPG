package com.mock.handlers;

import java.util.Stack;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;

import actions.TextAction;

public class ContactHandler implements ContactListener {

    public static Stack<String> zoneStrings = new Stack<String>();
    public static Stack<TextAction> textActions = new Stack<TextAction>();
    
    @Override
    public void beginContact(Contact contact) {
        String a = (String) contact.getFixtureA().getBody().getUserData().toString();
        String b = (String) contact.getFixtureB().getBody().getUserData().toString();
        Body playerBody = null;
        Body otherBody = null;
        if (a.equals("player")) {
            playerBody = contact.getFixtureA().getBody();
            otherBody = contact.getFixtureB().getBody();
        } else {
            playerBody = contact.getFixtureB().getBody();
            otherBody = contact.getFixtureA().getBody();
        }
        // figure out where the userdata needs to be put
        if (otherBody.getUserData().getClass().equals(TextAction.class)) {
            textActions.push((TextAction) otherBody.getUserData());
        }
        if (otherBody.getUserData().getClass().equals(String.class)) {
            zoneStrings.push(otherBody.getUserData().toString());
        }
    }

    @Override
    public void endContact(Contact contact) {}
    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {}
    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {}

}
