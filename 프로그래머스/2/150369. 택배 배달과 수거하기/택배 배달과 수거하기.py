def solution(cap, n, deliveries, pickups):
    answer = 0
    distance=n
    i, j=n-1, n-1
    
    while i>=0 or j>=0:
        deliver_cap=cap
        pickup_cap=cap
        
        if deliveries[i]!=0 or pickups[j]!=0:
            answer+=distance*2
            while deliver_cap>0:
                while deliveries[i]==0 and i!=-1:
                    i-=1
                deliveries[i]-=1
                deliver_cap-=1
                
            while pickup_cap>0:
                while pickups[j]==0 and j!=-1:
                    j-=1
                pickups[j]-=1
                pickup_cap-=1
                
            while deliveries[i]==0 and i!=-1:
                i-=1
            while pickups[j]==0 and j!=-1:
                j-=1
                
        else:
            i-=1
            j-=1
        distance=max(i,j)+1
    
    return answer