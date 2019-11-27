import java.util.*;

public class IronHeadHelper
{
	static Random rand = new Random();
	public static List<Vec3i> explosion(Vec3i pos, int size)
	{
		Set<Vec3i> set = new HashSet<Vec3i>();
		for (int i = -5; i <= 5; i++)
			for (int j = -5; j <= 5; j++)
				for (int k = -5; k <= 5; k++)
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
		return piston.getManhattanDistance(power) <= 3;
	}

	public static void main(String[] args)
	{
		int x, y, z;
		int size = 4;
		Scanner s = new Scanner(System.in);
		System.out.println("Input the block position of the piston (x, y, z)");
		x = s.nextInt();
		y = s.nextInt();
		z = s.nextInt();
		s.close();
		Vec3i pos = new Vec3i(x, y, z);
		System.out.println("Calculating...");

		List<Vec3i> ret = explosion(pos, size);
		List<Result> ans = new ArrayList<>();
		for (Vec3i j: ret)
		{
			// Power source needs to be destroyed first
			if (j.equals(pos))
			{
				break;
			}
			ans.add(new Result(j, pos.getManhattanDistance(j)));
		}

		ans.sort(Result::compareTo);
		Collections.reverse(ans);
		System.out.println("You need to place the power source at: ");
		int counter = 0;
		for (Result i: ans)
		{
			String msg = String.format("Position = [%d, %d, %d] Distance = %d", i.pos.x, i.pos.y, i.pos.z, i.distance);
			msg += String.format(" /setblock %d %d %d", i.pos.x, i.pos.y, i.pos.z);
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
