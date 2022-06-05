package Data;

public class PriorityQueueClass{
    public int size;
    public Usuario[] H;
  
    public PriorityQueueClass(Usuario[] array){
      H = array;
      size=-1;
    }  
    public void sort(){
        Usuario[] a = new Usuario[size+1];
        Usuario x;
        int j=0;
        while(size!=0){
          x=this.removeMax();
          a[j]=x;
          j++;
        }
        a[j]=H[0];
        for(int i =0;i<a.length;i++){
          System.out.println(a[i]);
        }
      }
  
    public Usuario removeMax(){
      Usuario res = (Usuario)H[0];
      H[0] = H[size--];
      siftDown(0);
      return res;
    }
    public int Parent(int i){
        if(i%2 == 0){
            i--;
        }
      return (i/2);
    }
    public int LeftChild(int i){
      return (2*i)+1;
    }
    public int RightChild(int i){
      return (2*i)+2;
    }
    public void remove(int i){
        //int infinito = (int)Double.POSITIVE_INFINITY;
        System.out.println(H[i]);
        H[i].setid("99999999");
        siftUp(i);
        removeMax();
        System.out.println("se elimino");
      }
    public void siftUp(int i){
      while (i>=1 && Integer.parseInt(H[Parent(i)].getIdUsuario())< Integer.parseInt(H[i].getIdUsuario())){
        Usuario temp = H[Parent(i)];
        H[Parent(i)]=H[i];
        H[i]=temp;
        i=Parent(i);
      }
    }
    public void print(){
      for(int i =0;i<size;i++){
        System.out.println(H[i].toString());
      }
    }
    public void siftDown(int i){
      int maxIndex = i;
      int l = LeftChild(i);
      if(l<=size && Integer.parseInt(H[l].getIdUsuario()) > Integer.parseInt(H[maxIndex].getIdUsuario())){
        maxIndex =l;
      }
      int r = RightChild(i);
      if(r<=size && Integer.parseInt(H[r].getIdUsuario()) > Integer.parseInt(H[maxIndex].getIdUsuario())){
        maxIndex =r;
      }
      if (i != maxIndex){
        Usuario temp = H[maxIndex];
        H[maxIndex]=H[i];
        H[i]=temp;
        siftDown(maxIndex);
      }
        
    }
    public void insert(Usuario p){
      if(size<H.length){
        size++;  
        H[size]=p;
        siftUp(size);
        
      }
    }
  }
  