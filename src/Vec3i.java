import static java.lang.Math.floor;

public class Vec3i implements Comparable<Vec3i>
{
    public static final Vec3i NULL_VECTOR = new Vec3i(0, 0, 0);
    public final int x;
    public final int y;
    public final int z;

    public Vec3i(int xIn, int yIn, int zIn)
    {
        this.x = xIn;
        this.y = yIn;
        this.z = zIn;
    }

    public Vec3i(double xIn, double yIn, double zIn)
    {
        this((int)floor(xIn), (int)floor(yIn), (int)floor(zIn));
    }

    @Override
    public String toString()
    {
        return String.format("[%d, %d, %d]", x, y, z);
    }

    public boolean equals(Object p_equals_1_)
    {
        if (this == p_equals_1_)
        {
            return true;
        }
        else if (!(p_equals_1_ instanceof Vec3i))
        {
            return false;
        }
        else
        {
            Vec3i vec3i = (Vec3i)p_equals_1_;

            if (this.getX() != vec3i.getX())
            {
                return false;
            }
            else if (this.getY() != vec3i.getY())
            {
                return false;
            }
            else
            {
                return this.getZ() == vec3i.getZ();
            }
        }
    }

    public int hashCode()
    {
        return (this.getY() + this.getZ() * 31) * 31 + this.getX();
    }

    public int compareTo(Vec3i p_compareTo_1_)
    {
        if (this.getY() == p_compareTo_1_.getY())
        {
            return this.getZ() == p_compareTo_1_.getZ() ? this.getX() - p_compareTo_1_.getX() : this.getZ() - p_compareTo_1_.getZ();
        }
        else
        {
            return this.getY() - p_compareTo_1_.getY();
        }
    }

    public int getX()
    {
        return this.x;
    }

    public int getY()
    {
        return this.y;
    }

    public int getZ()
    {
        return this.z;
    }

    public Vec3i crossProduct(Vec3i vec)
    {
        return new Vec3i(this.getY() * vec.getZ() - this.getZ() * vec.getY(), this.getZ() * vec.getX() - this.getX() * vec.getZ(), this.getX() * vec.getY() - this.getY() * vec.getX());
    }

    public int getManhattanDistance(Vec3i other)
    {
        return Math.abs(this.x - other.x) + Math.abs(this.y - other.y) + Math.abs(this.z - other.z);
    }

    public double getDistance(int xIn, int yIn, int zIn)
    {
        double d0 = (double)(this.getX() - xIn);
        double d1 = (double)(this.getY() - yIn);
        double d2 = (double)(this.getZ() - zIn);
        return Math.sqrt(d0 * d0 + d1 * d1 + d2 * d2);
    }

    public double getDistance(Vec3i other)
    {
        return this.getDistance(other.getX(), other.getY(), other.getZ());
    }

    public double distanceSq(double toX, double toY, double toZ)
    {
        double d0 = (double)this.getX() - toX;
        double d1 = (double)this.getY() - toY;
        double d2 = (double)this.getZ() - toZ;
        return d0 * d0 + d1 * d1 + d2 * d2;
    }

    public double distanceSqToCenter(double xIn, double yIn, double zIn)
    {
        double d0 = (double)this.getX() + 0.5D - xIn;
        double d1 = (double)this.getY() + 0.5D - yIn;
        double d2 = (double)this.getZ() + 0.5D - zIn;
        return d0 * d0 + d1 * d1 + d2 * d2;
    }

    public double distanceSq(Vec3i to)
    {
        return this.distanceSq((double)to.getX(), (double)to.getY(), (double)to.getZ());
    }

    public Vec3i up()
    {
        return new Vec3i(x, y + 1, z);
    }

    public Vec3i down()
    {
        return new Vec3i(x, y - 1, z);
    }

    public Vec3i south()
    {
        return new Vec3i(x, y, z + 1);
    }

    public Vec3i north()
    {
        return new Vec3i(x, y, z - 1);
    }

    public Vec3i east()
    {
        return new Vec3i(x + 1, y, z);
    }

    public Vec3i west()
    {
        return new Vec3i(x - 1, y, z);
    }
}
