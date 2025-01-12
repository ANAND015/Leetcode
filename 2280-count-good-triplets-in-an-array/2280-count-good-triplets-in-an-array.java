class Solution {
    long[] cnt1, cnt2;
    void update(long[] cnt, int o, int l, int r, int idx, long val){
        if(idx>r || idx<l) return;
        cnt[o]+=val;
        if(l==r) return;
        int m=(l+r)>>1; 
        update(cnt,o*2,l,m,idx,val);
        update(cnt,o*2+1,m+1,r,idx,val);
    }
    long query(long[] cnt, int o, int l, int r, int ql, int qr){     
        //System.out.println("o: "+o+" l: "+l+" r: "+r+" ql: "+ql+" qr: "+qr);
        if(ql>r || qr<l) return 0;
        if(ql<=l && qr>=r) return cnt[o];
        
        int m=(l+r)>>1; 
        return query(cnt,o*2,l,m,ql,qr)+query(cnt,o*2+1,m+1,r,ql,qr);
    }

    public long goodTriplets(int[] nums1, int[] nums2) {
        int n=nums1.length;
        HashMap<Integer,Integer> map=new HashMap<>();
        for(int i=0; i<n; i++) map.put(nums2[i],i);
        int[] nums=new int[n];
        for(int i=0; i<n; i++) nums[i]=map.get(nums1[i])+1;
       // System.out.println(Arrays.toString(nums));

        cnt1=new long[n<<2]; cnt2=new long[n<<2];
        long res=0;
        for(int i=0; i<n; i++){
            long less=query(cnt1,1,1,n,1,nums[i]-1);
            long pre=query(cnt2,1,1,n,1,nums[i]-1);
            res+=pre;
            update(cnt2,1,1,n,nums[i],less);
            update(cnt1,1,1,n,nums[i],1);
        }
        return res;
    }
}