import java.util.*;

public class IronHeadHelper
{
	public static List<Vec3i> explode(Vec3i pos)
	{
		Set<Vec3i> set = new HashSet<Vec3i>();
		int range = 5;
		for (int i = -range; i <= range; i++)
			for (int j = -range; j <= range; j++)
				for (int k = -range; k <= range; k++)
				{
					Vec3i blockPos = new Vec3i(pos.x + i, pos.y + j, pos.z + k);
					if (canPower(pos, blockPos))
					{
						set.add(blockPos);
					}
				}

		List<Vec3i> ret = new ArrayList<Vec3i>();
		ret.addAll(set);
		return ret;
	}

	static boolean canPower(Vec3i piston, Vec3i power)
	{
		if (power.getY() < 0)
		{
			return false;
		}
		Vec3i piston_up = new Vec3i(piston.getX(), piston.getY() + 1, piston.getZ());
		return piston.getManhattanDistance(power) <= 2 || piston_up.getManhattanDistance(power) <= 2;
	}

	public static List<Result> calc(Vec3i pos)
	{
		List<Vec3i> result = explode(pos);
		List<Result> ret = new ArrayList<>();
		for (Vec3i j: result)
		{
			// Power source needs to be destroyed first
			if (j.equals(pos))
			{
				break;
			}
			ret.add(new Result(j, pos.getManhattanDistance(j)));
		}
		ret.sort(Result::compareTo);
		Collections.reverse(ret);
		return ret;
	}

	public static void main(String[] args)
	{
		int x, y, z;
		Scanner s = new Scanner(System.in);
		System.out.println("Input the block position of the piston (x, y, z)");
		x = s.nextInt();
		y = s.nextInt();
		z = s.nextInt();
		s.close();
		List<Result> ans = calc(new Vec3i(x, y, z));
		System.out.println("Calculating...");
		System.out.println("You need to place the power source at: ");
		int counter = 0;
		for (Result i: ans)
		{
			String msg = String.format("Position = [%d, %d, %d] Distance = %d", i.pos.x, i.pos.y, i.pos.z, i.distance);
			msg += String.format("   /setblock %d %d %d", i.pos.x, i.pos.y, i.pos.z);
			msg += i.distance == 1 ? " redstone_block" : " lever";
			System.out.println(msg);
			if (++counter == 20)
			{
				break;
			}
		}
	}
	private static class Result implements Comparable<Result>
	{
		public Vec3i pos;
		public int distance;

		public Result(Vec3i pos, int distance)
		{
			this.pos = pos;
			this.distance = distance;
		}

		public int compareTo(Result o)
		{
			if (this.distance == o.distance)
			{
				return this.pos.compareTo(o.pos);
			}
			return -(this.distance - o.distance);
		}
	}
}
