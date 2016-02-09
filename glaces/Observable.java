package glaces;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Xavier on 06/02/2016.
 */
public class Observable  {
    private List<IObservateur> observateurs;

    public Observable() {
        this.observateurs = new LinkedList<IObservateur>();
    }

    public void notifierObservateurs()  {
        Iterator<IObservateur> it = this.observateurs.iterator();
        // Notifier tous les observers
        while(it.hasNext()){
            IObservateur obs = it.next();
            obs.notifier();
        }
    }

    void ajouterObservateur(IObservateur observateur)  {

        // On pourrait placer cet observateur en dernier (implémenté en push, plus commun).
        this.observateurs.add(observateur);
    }

    void supprimerObservateur(IObservateur observateur)  {
        // Enlever un abonné a la liste
        this.observateurs.remove(observateur);
    }


}