class GenNode {
	private GenNode _leftchild;
	private GenNode _rightsib;
	private char _element;

	public GenNode(char element) {
		_element = element;
	}

	public GenNode(char element, GenNode leftchild, GenNode rightsib) {
		_element = element;
		_leftchild = leftchild;
		_rightsib = rightsib;
	}

	GenNode leftchild() {
		return _leftchild;
	}

	GenNode rightsib() {
		return _rightsib;
	}

	char element() {
		return _element;
	}

	void setLeftchild(GenNode leftchild) {
		_leftchild = leftchild;
	}

	void setRightsib(GenNode rightsib) {
		_rightsib = rightsib;
	}

	void setElement(char element) {
		_element = element;
	}
}