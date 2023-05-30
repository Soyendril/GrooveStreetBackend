package com.GrooveSpring.conversation;

import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@CrossOrigin
public class ConversationController {
    private final ConversationService conversationService;

    public ConversationController(ConversationService conversationService) {
        this.conversationService = conversationService;
    }

    /**
     * Post : cree une nouvelle conversation
     * @param conversation
     * @return
     */
    @PostMapping()
    public Conversation create(@RequestBody Conversation conversation){
        return conversationService.createConversation(conversation);
    }

    /**
     * Renvoi toutes les conversations
     * @return
     */
    @GetMapping()
    public List<Conversation> read(){
        return conversationService.getConversation();
    }

    /**
     * Liste uniquemement les conversations pour un id (pas le detail)
     * @param id
     * @return
     */
    @GetMapping("{id}")
    public List<Conversation> getAllByUserIdGroupByUser2(@PathVariable Long id){
        return conversationService.getAllByUserIdGroupByUser2(id);
    }

    /**
     * Renvoi une liste des utilisateurs groupés par colonne envoyeur/receveur
     * @param id
     * @param id2
     * @return
     */
    @GetMapping("/unique/{id}")
    public List<Conversation> getAllByUserIdGroupByUser2(@PathVariable Long id, @RequestParam Long id2){
        return conversationService.getAllConversByid1AndId2(id, id2);
    }

    /**
     * Met à jour une conversation
     * @param id
     * @param conversation
     * @return
     */
    @PutMapping("{id}")
    public Conversation update(@PathVariable Long id, @RequestBody Conversation conversation){
        return conversationService.updateConversation(id, conversation);
    }

    /**
     * Supprime une conversation
     * @param id
     */
    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        conversationService.deleteConversation(id);
    }
}
