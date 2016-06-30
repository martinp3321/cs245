

class NodeS {

        String data;
        NodeS next;

        public NodeS(String value) {
            this.data = value;
        }
        
        public NodeS(NodeS other){
            this.data = other.data;
        }

        public String getValue() {
            return data;
        }

        public void setValue(String data) {
            this.data = data;
        }

        public NodeS getNext() {
            return next;
        }

        public void setNext(NodeS next) {
            this.next = next;
        }

    }