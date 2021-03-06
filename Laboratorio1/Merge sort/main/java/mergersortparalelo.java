//By : Anthony Reynold Alcarraz Mucha 

public class mergersortparalelo {
    
    public void parallelmergersort(int[] arr, int left, int right, int numofthreads) {
        int middleindex =(left + right)/2;
        
        if ( numofthreads <= 1){
                sort(arr,left,right);
             return;
        }
        
        //Generador de los hilos que trabajan en cara sub array
        Thread leftsorter = mergersortParallel(arr,left,middleindex,numofthreads);
        Thread rightsorter = mergersortParallel(arr,middleindex+1,right,numofthreads);
        
        
        //Incializacion de hilos
        leftsorter.start();
        rightsorter.start();
        
        // Joins para la espera de los hilos que configuran las subcadenas
        try {
            leftsorter.join();
            rightsorter.join();
        } catch (InterruptedException e) {
            
        }
        
        merge(arr,left,middleindex,right);
    }
    
    
    // Creacion de la subclase
    public Thread mergersortParallel (int arr[],int left, int right,int numofthreads){
        //Retorna un nuevo hilo creado
        return new Thread(){
            public void run(){
                parallelmergersort(arr,left,right,numofthreads/2);
            }
        };       
    }

    public void sort( int arr[],int left, int right){
        if (left<right){
            int middle = (left +right)/2;
            sort(arr,left,middle);
            sort(arr, middle+1,right);
            merge(arr,left,middle,right);
        }
    }
    // Funcion merger que hara la union de los numeros comparados
    public void merge(int arr[],int left,int middle,int right){ 
        
        // Longidudes de los vectores 
        int n1 = middle - left + 1;
        int n2 = right - middle;

        //Arrays temporales
        int leftarray[] = new int [n1];
        int rightarray[] = new int [n2];
        
        for (int i=0;i<n1;i++){
            leftarray[i]= arr[left + i];
        }

        for (int j=0;j<n2;j++){
            rightarray[j]= arr[middle + j + 1];
        }

        int i=0,j=0;
        int k=left;
        //Ordenamiento
        while (i<n1 && j<n2){
            if(leftarray[i]<rightarray[j]){
                arr[k]=leftarray[i];
                i++;
            }else{
                arr[k]=rightarray[j];
                j++;
            }
            k++;
        }
        while(i< n1){
            arr[k]=leftarray[i];
            i++;
            k++;
        }
        while(j<n2){
            arr[k]=rightarray[j];
            j++;
            k++;
        }
    }
    public void imprimir(int arr[]){
        int n = arr.length;
        for(int i=0;i<n;i++){
            System.out.print(arr[i] + " ");
        }
    }
}

