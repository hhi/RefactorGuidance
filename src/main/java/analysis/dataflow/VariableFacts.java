package analysis.dataflow;


import java.util.List;

// Kunnen we hier 'live' automatisch instellen, op basis van eerdere waarden?
public class VariableFacts {

        public boolean write;
        public List<Loc> written_at;

        public boolean read;
        public List<Loc> read_at;

        public boolean cond_write;

        public boolean live; // A variable is written which before only has been read in the section

        private class Loc {
                public int lineNumber;
                public int statementIndex;
        }
}