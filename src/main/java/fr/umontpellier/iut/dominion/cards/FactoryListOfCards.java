package fr.umontpellier.iut.dominion.cards;

import fr.umontpellier.iut.dominion.ListOfCards;
import fr.umontpellier.iut.dominion.cards.base.*;
import fr.umontpellier.iut.dominion.cards.common.*;

import java.util.HashMap;

public abstract class FactoryListOfCards {
    private static final HashMap<String, Class<? extends Card>> dicoCards = new HashMap<>();

    static {
        dicoCards.put("Copper", Copper.class);
        dicoCards.put("Silver", Silver.class);
        dicoCards.put("Gold", Gold.class);
        dicoCards.put("Estate", Estate.class);
        dicoCards.put("Duchy", Duchy.class);
        dicoCards.put("Province", Province.class);
        dicoCards.put("Curse", Curse.class);
        dicoCards.put("Cellar", Cellar.class);
        dicoCards.put("Chapel", Chapel.class);
        dicoCards.put("Village", Village.class);
        dicoCards.put("Moat", Moat.class);
        dicoCards.put("Workshop", Workshop.class);
        dicoCards.put("Vassal", Vassal.class);
        dicoCards.put("Militia", Militia.class);
        dicoCards.put("ThroneRoom", ThroneRoom.class);
        dicoCards.put("Laboratory", Laboratory.class);
        dicoCards.put("Witch", Witch.class);
        dicoCards.put("Harbinger", Harbinger.class);
        dicoCards.put("Bureaucrat", Bureaucrat.class);
        dicoCards.put("CouncilRoom", CouncilRoom.class);
        dicoCards.put("Festival", Festival.class);
        dicoCards.put("Gardens", Gardens.class);
        dicoCards.put("Library", Library.class);
        dicoCards.put("Market", Market.class);
        dicoCards.put("Mine", Mine.class);
        dicoCards.put("Moneylender", Moneylender.class);
        dicoCards.put("Remodel", Remodel.class);
        dicoCards.put("Smithy", Smithy.class);
        dicoCards.put("Sentry", Sentry.class);
        dicoCards.put("Poacher", Poacher.class);
        dicoCards.put("Merchant", Merchant.class);
        dicoCards.put("Artisan", Artisan.class);
        dicoCards.put("Bandit", Bandit.class);
    }

    public static ListOfCards createCardList(int number, String nameOfCardClass) {
        ListOfCards stack = new ListOfCards();
        for (int i = 0; i < number; i++) {
            try {
                stack.add(dicoCards.get(nameOfCardClass).newInstance());
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return stack;
    }

    protected abstract Card createCard();
}
