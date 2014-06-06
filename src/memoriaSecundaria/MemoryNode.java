package memoriaSecundaria;

import java.io.IOException;
import java.nio.ByteBuffer;

public class MemoryNode {
	private final static int bufferSize = 54;
	private long rightPos;
	private long leftPos;
	private double x;
	private double y;
	private char isLeaf;
	private long pos;
	private int visitado;
	private long padrePos;
	private static MemoryManager manager;

	public MemoryNode(double x, double y, char leaf, long rightPos, long leftPos)
			throws IOException {
		isLeaf = leaf;
		this.x = x;
		this.y = y;
		this.pos = manager.getNewPosition();
		// Como el archivo no puede tener posiciones negativas, un numero
		// negativo es el null
		this.rightPos = rightPos;
		this.leftPos = leftPos;
		this.visitado = 0;
		this.padrePos = -1;
		this.manager.writeBuff(this.toBuff(), this.pos);
	}
	
	public MemoryNode(double x, double y, char leaf, long rightPos, long leftPos, int visitado, long padrePos)
			throws IOException {
		isLeaf = leaf;
		this.x = x;
		this.y = y;
		this.pos = manager.getNewPosition();
		// Como el archivo no puede tener posiciones negativas, un numero
		// negativo es el null
		this.rightPos = rightPos;
		this.leftPos = leftPos;
		this.visitado = visitado;
		this.padrePos = padrePos;
		this.manager.writeBuff(this.toBuff(), this.pos);
	}
	
	public MemoryNode(double x, double y, char leaf, long rightPos, long leftPos, int visitado, long padrePos, long pos)
			throws IOException {
		isLeaf = leaf;
		this.x = x;
		this.y = y;
		
		// Como el archivo no puede tener posiciones negativas, un numero
		// negativo es el null
		this.rightPos = rightPos;
		this.leftPos = leftPos;
		this.visitado = visitado;
		this.padrePos = padrePos;
		this.pos = pos;
		this.manager.writeBuff(this.toBuff(), this.pos);
	}

	// esto hay que hacerlo apenas esté creado el memory manager
	public static void setMemoryManager(MemoryManager man) {
		manager = man;
	}

	public void setRight(long pos) throws IOException {
		this.rightPos = pos;
		this.manager.writeBuff(this.toBuff(), this.pos);

	}

	private byte[] toBuff() {
		// orden de nodo: posLeft, pos, posRight, x, y, isleaf
		byte[] b = new byte[bufferSize];
		ByteBuffer buff = ByteBuffer.allocate(bufferSize);
		buff = ByteBuffer.wrap(b);
		buff.position(0);
		buff.putLong(this.leftPos);
		buff.putLong(this.pos);
		buff.putLong(this.rightPos);
		buff.putDouble(this.x);
		buff.putDouble(this.y);
		buff.putChar(this.isLeaf);
		buff.putInt(this.visitado);
		buff.putLong(this.padrePos);
		buff.position(0);
		buff.get(b);
		return b;
	}

	private static MemoryNode getNodeFromBuff(byte[] b) throws IOException {
		// orden de nodo: posLeft, pos, posRight, x, y, isleaf
		ByteBuffer buff = ByteBuffer.allocate(bufferSize);
		buff = ByteBuffer.wrap(b);
		buff.position(0);
		long posLeft, posRight, pos;
		double x, y;
		char isLeaf;
		int visitado;
		long padrePos;
		posLeft = buff.getLong();
		pos = buff.getLong();
		posRight = buff.getLong();
		x = buff.getDouble();
		y = buff.getDouble();
		isLeaf = buff.getChar();
		visitado = buff.getInt();
		padrePos = buff.getLong();
		MemoryNode n = new MemoryNode(x, y, isLeaf, posRight, posLeft, visitado, padrePos, pos);
		return n;
	}

	public MemoryNode getRight() throws IOException {
		if (rightPos == -1) {
			return null;
		}
		byte[] b = this.manager.readBuff(rightPos);
		return this.getNodeFromBuff(b);
	}
	
	public MemoryNode getPadre() throws IOException {
		if (padrePos == -1) {
			return null;
		}
		byte[] b = this.manager.readBuff(padrePos);
		return this.getNodeFromBuff(b);
	}

	public void setLeft(long pos) throws IOException {
		this.leftPos = pos;
		this.manager.writeBuff(this.toBuff(), this.pos);

	}

	public MemoryNode getLeft() throws IOException {
		if (leftPos == -1) {
			return null;
		}
		byte[] b = this.manager.readBuff(leftPos);
		return this.getNodeFromBuff(b);
	}

	public int height() throws IOException {
		if (this.leftPos < 0 && this.rightPos < 0)
			return 0;
		else if (this.rightPos < 0)
			return 1 + this.getNodeFromBuff(manager.readBuff(leftPos)).height();
		else if (this.leftPos < 0)
			return 1 + this.getNodeFromBuff(manager.readBuff(rightPos))
					.height();
		return 1 + this.getNodeFromBuff(manager.readBuff(leftPos)).height()
				+ this.getNodeFromBuff(manager.readBuff(rightPos)).height();
	}

	public int size() throws IOException {
		if (this.leftPos < 0 && this.rightPos < 0)
			return 1;
		else if (this.rightPos < 0)
			return 1 + this.getNodeFromBuff(manager.readBuff(leftPos)).size();
		else if (this.leftPos < 0)
			return 1 + this.getNodeFromBuff(manager.readBuff(rightPos)).size();
		return 1 + this.getNodeFromBuff(manager.readBuff(leftPos)).size()
				+ this.getNodeFromBuff(manager.readBuff(rightPos)).size();
	}

	public double getX() {
		return this.x;
	}

	public double getY() {
		return this.y;
	}

	public long getPos() {
		return pos;
	}

	public void setX(double x) {
		this.x = x;
	}

	public void setY(double y) {
		this.y = y;
	}

	public char isLeaf() {
		return this.isLeaf;
	}

	public void setIsLeaf(char b) {
		this.isLeaf = b;
	}

	public long getPadrePos() {
		return padrePos;
	}

	public void setPadrePos(long padre) throws IOException {
		this.padrePos = padre;
		this.manager.writeBuff(this.toBuff(), this.pos);
	}

	public int getVisitado() {
		return visitado;
	}

	public void setVisitado(int visitado) throws IOException {
		this.visitado = visitado;
		this.manager.writeBuff(this.toBuff(), this.pos);
	}

}
