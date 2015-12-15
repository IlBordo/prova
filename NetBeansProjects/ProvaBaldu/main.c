#include <stdio.h>
#include <stdlib.h>

/*
 STRUTTURE
 Il meccanismo di struttura ci fornisce un modo per aggregare variabili di tipo differente
 
 struct carte
 {
 char seme;
 int valore;
 };
 carte è il nome della struttura, seme e valore sono i membri della struttura.
 
 Un modo per fare le due cose insieme è:
 
 struct carte
 {
 char seme;
 int valore;
 }c1, c2; 
 */

/*
struct complex {
    double re; //parte reale
    double im; //parte immaginaria
};

typedef struct complex complex;

void add (complex *a, complex b, complex c) {
    a->re = b.re+c.re;
    a->im = b.im+c.im;
} // a->re è equivalente a (*a).re *(a.re)

void moltiplica (complex *a, complex b, complex c) {
    a->re = b.re*c.re - b.im*c.im;
    a->im = b.re*c.im + b.im*c.re;
}
*/

/*
struct studente {
        char *cognome;
        int matricola;
        int voto;
    };

int fail(struct studente classe[]) {
    int i, cnt = 0;
    for(i=0; i<10; i++)
        if (classe[i].voto<18)
            cnt++;            
    return cnt;
}
*/

typedef struct elemento {
    char dato;
    struct elemento *next;
}elemento;

typedef struct pila {
    int cnt;
    elemento *head;
    elemento *tail;
}pila;

void inizializzaPila (pila *P) {
    P->cnt = 0;
    P->head = NULL;
    P->tail = NULL;
}

void stampaPila(pila *P) {
    elemento *temp = P->head;
    while (temp != NULL) {
        printf ("%c ", temp->dato);
        temp = temp->next;
    }
    printf("\n");
}

void creaPila (pila *P) {
    char scelta;
    printf("Vuoi inserire un elemento ? (s/n)\n");
    scanf(" %c", &scelta);
    if (scelta == 's') {
        elemento *temp = (elemento *) (malloc(sizeof(elemento)));
        temp->dato = rand()%200;
        temp->next = NULL;
        
        P->head = P->tail = temp;
        P->cnt++;
        while(scelta == 's') {
            printf("Vuoi inserire un altro elemento ? (s/n)\n");
            scanf(" %c", &scelta);
            if (scelta == 's') {
                elemento *temp = (elemento *) (malloc(sizeof(elemento)));
                temp->dato = rand()%200;
                temp->next = P->head;
                
                P->head = temp;
                P->cnt++;
            }
        }
    }
}

void inserisciStringaInPila (char S[], pila *P) {    
    if (S[0] == '\0') {
        printf("La stringa è vuota\n");
    }
    else {
        elemento *temp = (elemento*) malloc(sizeof(elemento));
        
        temp->dato = S[0];
        temp->next = NULL;
        
        P->head = P->tail = temp;
        P->cnt++;
        
        int i = 1;
        while(S[i] != '\0') {
            temp = (elemento *) malloc(sizeof(elemento));
            temp->dato = S[i];
            temp->next = P->head;
            
            P->head = temp;
            P->cnt++;
            i++;
        }
    }
}

void cancellaElementoPila (pila *P, char d) {
    if (P->head->dato == d) {
        elemento *temp = P->head->next;
        free(P->head);
        P->head = temp;
        if (P->head == NULL)
            P->tail = NULL;
        P->cnt--;
        printf("L'elemento %c è stato eliminato dalla lista\n", d);
    }
    else {
        elemento *temp = P->head;
        while(temp != NULL) {
            if (temp->next->dato == d) {
                elemento *temp2 = temp->next;
                temp->next = temp->next->next;
                free(temp2);
                P->cnt--;
                printf("L'elemento %c è stato eliminato dalla lista\n", d);
                if (P->head == NULL)
                    P->tail = NULL;
                break;
            }
            temp = temp->next;
        }
    }
}

void sostituisciElementoPila (elemento *testa, char daSostituire, char sostituto) {
    if (testa != NULL) {
        if (testa->dato == daSostituire) {
            testa->dato = sostituto;
            sostituisciElementoPila (testa->next, daSostituire, sostituto);
        }
    }
}

void aggiungiDopoDato (pila *P, elemento *testa, char d, char dPosizione) {
    if (testa != NULL) {
        elemento *temp = testa;
        if (temp->dato == dPosizione) {
            elemento *nuovo = (elemento *) (malloc(sizeof(elemento)));
            nuovo->dato = d;
            nuovo->next = temp->next;
            temp->next = nuovo;
            P->cnt++;
            aggiungiDopoDato(P, testa->next, d, dPosizione);
        }
        else
            aggiungiDopoDato(P, testa->next, d, dPosizione);
    }
}

typedef struct elem {
    int d;
    struct elem *next;
}elem;

typedef struct coda {
    int cnt;
    elem *testa;
    elem *tail;
}coda;

void inizializzaCoda (coda *Q) {
    Q = malloc(sizeof(coda));
    Q->cnt = 0;
    Q->tail = NULL;
    Q->testa = NULL;
    
}

void incoda (coda *Q, int dato) {
    elem *temp = malloc(sizeof(elem));
    temp->d = dato;
    temp->next = NULL;
    if (Q->cnt == 0) {
        Q->tail = temp;
        Q->testa = Q->tail;
    }
    else {
        Q->tail->next = temp;
        Q->tail = Q->tail->next;
    }
    Q->cnt++;
}

//void estrai (coda *Q, int *dato) {
//    elem *temp = Q->testa;
//    if (Q->cnt != 0) {
//        
//    }
//}

void stampaCoda(coda *Q) {
    elem *temp = Q->testa;
    while (temp != NULL) {
        printf ("%d ", temp->d);
        temp = temp->next;
    }
    printf("\n");
}

int main(int argc, char** argv) {
    
    /*1)complex a, b, c;
    printf("Inserisci la parte reale del primo numero \n");
    scanf("%f", &b.re);
    
    printf("Inserisci la parte immaginaria del primo numero \n");
    scanf("%f", &b.im);
    
    printf("Inserisci la parte reale del secondo numero \n");
    scanf("%f", &c.re);
    
    printf("Inserisci la parte immaginaria del secondo numero \n");
    scanf("%f", &c.im);
    
    add(&a, b, c);
    
    printf("La parte reale del risultato è %f \n", a.re);
    printf("La parte immaginaria del risultato è &f \n", a.im);
    */
    
    /*2)struct studente classe [N];
    int i, k;
    
    for (i=0; i<N; i++) {
        printf("Inserisci la matricola dello studente %d \n", i);
        scanf("&d", &classe[i].matricola);
        printf("Inserisci il voto \n");
        scanf("%d", &classe[i].voto);
    }
    
    k = fail(classe);
    printf("%d studenti non hanno passato il test \n", k); 
    */
    
    pila P1;
    
    char S[5] = {'c', 'i', 'a', 'o', '\0'};
    
    inizializzaPila(&P1);
    
    inserisciStringaInPila(S, &P1);
    
    stampaPila(&P1);
    
    return (EXIT_SUCCESS);
}